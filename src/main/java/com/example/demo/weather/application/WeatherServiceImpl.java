package com.example.demo.weather.application;

import com.example.demo.weather.doamin.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Double> tempYear(int year) {
        List<Double> rtn = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            rtn.add(tempYearUnit(year + i));
        }
        return rtn;
    }

    private Double tempYearUnit(int year) {
        Double rtn = weatherRepository.findAveByYear(year).orElse((double) 0);
        return rtn;
    }

    @Override
    public List<Double> tempMonth(int year) {
        List<Double> rtn = new ArrayList<>();
        for(int month = 1; month <= 12; month++) {
            rtn.add(tempMonthUnit(year, month));
        }
        return rtn;
    }

    private Double tempMonthUnit(int year, int month) {
        Double rtn = weatherRepository.findAveByYearAndMonth(year, month).orElse((double) 0);
        return rtn;
    }
}
