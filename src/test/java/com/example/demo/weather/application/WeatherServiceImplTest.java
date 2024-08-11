package com.example.demo.weather.application;

import com.example.demo.weather.doamin.WeatherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @InjectMocks
    WeatherServiceImpl weatherService;

    @Mock
    WeatherRepository weatherRepository;

    @Test
    @DisplayName("온도 데이터 받아오기(Year)")
    void tempYear() {
        int giveData = 2000;
        List<Double> expectedList = new ArrayList<>();
        Double value = 10.0;

        // 스텁 설정
        for (int i = 0; i < 10; i++) {
            expectedList.add(value + i);
            lenient().when(weatherRepository.findAveByYear(giveData + i)).thenReturn(Optional.of(value + i));
        }

        List<Double> result = weatherService.tempYear(giveData);

        Assertions.assertThat(result).hasSize(expectedList.size());
        for (int i = 0; i < result.size(); i++) {
            Assertions.assertThat(result.get(i)).isEqualTo(expectedList.get(i));
        }
    }

    @Test
    @DisplayName("온도 데이터 받아오기(Month)")
    void tempMonth() {
        int giveData = 2000;
        List<Double> expectedList = new ArrayList<>();
        Double value = 10.0;

        // 스텁 설정
        for (int month = 1; month <= 12; month++) {
            expectedList.add(value + month);
            lenient().when(weatherRepository.findAveByYearAndMonth(giveData, month)).thenReturn(Optional.of(value + month));
        }

        List<Double> result = weatherService.tempMonth(giveData);

        Assertions.assertThat(result).hasSize(expectedList.size());
        for (int i = 0; i < result.size(); i++) {
            Assertions.assertThat(result.get(i)).isEqualTo(expectedList.get(i));
        }
    }
}