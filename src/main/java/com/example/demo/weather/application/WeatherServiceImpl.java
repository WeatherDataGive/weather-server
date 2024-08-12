package com.example.demo.weather.application;

import com.example.demo.config.RunTimer;
import com.example.demo.weather.application.WeatherService;
import com.example.demo.weather.doamin.Weather;
import com.example.demo.weather.doamin.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final Map<Integer, Double> yearCash = new HashMap<>();

    @Override
    @RunTimer(method = "SELECT * FROM weather_table WHERE year=2000 AND month=3")
    public Double year(int year) {
        if(getCash(year) != null) return yearCash.get(year);

        double sum = 0;
        int count = 0;
        for(int month = 1; month <= 12; month++) {
            List<Weather> savedData = weatherRepository.findAllByYearAndMonth(year, month);
            for(Weather d : savedData) {
                sum += d.getTemp();
                count++;
            }
        }
        sum /= count;
        putCash(year, sum);

        return sum;
    }

    private Double getCash(int year) {
        if(yearCash.containsKey(year)) return yearCash.get(year);
        return null;
    }

    private void putCash(int year, double data) {
        yearCash.put(year, data);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Double> month(int year) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Double> day(int year, int month) {
        return null;
    }
}
