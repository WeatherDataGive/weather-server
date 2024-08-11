package com.example.demo.weather.application;

import java.util.List;

public interface WeatherService {
    List<Integer> tempYear(int year);
    List<Integer> tempMonth(int year);
}
