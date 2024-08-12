package com.example.demo.weather.presentation;

import com.example.demo.weather.application.WeatherFactory;
import com.example.demo.weather.application.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherFactory weatherFactory;

    @GetMapping("/{domain}")
    public ResponseEntity<List<Double>> getTemp(@PathVariable("domain") String domain,
                                                @RequestParam("identifier") String identifier,
                                                @RequestParam("year") int year,
                                                @RequestParam(value = "month", defaultValue = "0") int month) {

        WeatherService weatherService = weatherFactory.getInstance(domain);

        if(DataIdentifier.YEAR.getIdentifier().equals(identifier)) {
            List<Double> rtn = List.of(weatherService.year(year));
            return new ResponseEntity<>(rtn, HttpStatus.OK);
        }

        if(DataIdentifier.MONTH.getIdentifier().equals(identifier)) {
            List<Double> rtn = weatherService.month(year);
            return new ResponseEntity<>(rtn, HttpStatus.OK);
        }

        if(DataIdentifier.DAY.getIdentifier().equals(identifier)) {
            if(month == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            List<Double> rtn = weatherService.day(year, month);
            return new ResponseEntity<>(rtn, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
