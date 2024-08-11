package com.example.demo.weather.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeatherIdentifier {
    YEAR("year"),
    MONTH("month");
    private final String identifier;
}
