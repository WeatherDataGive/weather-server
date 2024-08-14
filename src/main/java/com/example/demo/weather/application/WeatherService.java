package com.example.demo.weather.application;

import java.util.List;

public interface WeatherService {
    Double year(int year, String dataType);
    Double month(int year, int month, String dataType);
    List<Double> day(int year, int month, String dataType);
}
