package com.example.demo.weather.application.implement;

import com.example.demo.weather.application.WeatherService;
import com.example.demo.weather.doamin.implement.TempRepository;
import com.example.demo.weather.doamin.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TempService implements WeatherService {

    private final TempRepository tempRepository;

    @Override
    @Transactional(readOnly = true)
    public Double year(int year) {
        Double savedData = tempRepository.findAveTempByYear(year).orElse((double) 0);
        return savedData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Double> month(int year) {
        List<Double> savedDataList = new ArrayList<>();
        for(int month = 1; month <= 12; month++) {
            savedDataList.add(monthUnit(year, month));
        }
        return savedDataList;
    }

    private Double monthUnit(int year, int month) {
        Double savedData = tempRepository.findAveTempByYearAndMonth(year, month).orElse((double) 0);
        return savedData;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Double> day(int year, int month) {
        List<WeatherData.TempDataOfDay> savedData = tempRepository.findAllByYearAndMonth(year, month);
        List<Double> savedTypeChangeData = savedData.stream().map(data -> (double) data.temp()).toList();
        return savedTypeChangeData;
    }
}
