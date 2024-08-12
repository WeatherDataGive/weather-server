package com.example.demo.weather.application;

import java.util.List;

public interface WeatherService {
    Double year(int year);
    List<Double> month(int year);
    List<Double> day(int year, int month);
}
