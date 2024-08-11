package com.example.demo.weather.application;

import com.example.demo.weather.doamin.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Integer> tempYear(int year) {
        Double savedEntityList = weatherRepository.findAveByYear(year).get();
        return null;
    }

    @Override
    public List<Integer> tempMonth(int year) {
        return null;
    }
}
