package com.example.demo.weather;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    @GetMapping
    public void getTemp(@RequestParam("data") String data) {
        return;
    }
}
