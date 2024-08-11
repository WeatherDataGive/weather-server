package com.example.demo.weather.presentation;

import com.example.demo.weather.application.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/temp")
    public ResponseEntity<List<Double>> getTemp(@RequestParam("identifier") String identifier,
                                                 @RequestParam("year") int year) {
        if(WeatherIdentifier.YEAR.getIdentifier().equals(identifier)) {
            List<Double> rtn = weatherService.tempYear(year);
            return new ResponseEntity<>(rtn, HttpStatus.OK);
        }

        if(WeatherIdentifier.MONTH.getIdentifier().equals(identifier)) {
            List<Double> rtn = weatherService.tempMonth(year);
            return new ResponseEntity<>(rtn, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
