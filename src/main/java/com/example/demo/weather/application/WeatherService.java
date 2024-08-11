package com.example.demo.weather.application;

import java.util.List;

public interface WeatherService {
    List<Double> tempYear(int year);
    List<Double> tempMonth(int year);
}
