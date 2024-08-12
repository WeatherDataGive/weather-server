package com.example.demo.weather.application;

import com.example.demo.weather.application.implement.TempService;
import com.example.demo.weather.doamin.implement.TempRepository;
import com.example.demo.weather.doamin.WeatherData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TempServiceTest {

    @InjectMocks
    TempService tempService;

    @Mock
    TempRepository tempRepository;


    @Nested
    @DisplayName("온도 데이터 받아오기(Year) 테스트")
    class tempYear {
        @Test
        @DisplayName("정상 테스트")
        void test1() {
            int yearData = 2000;
            double rtnValue = 10.0;

            when(tempRepository.findAveTempByYear(yearData)).thenReturn(Optional.of(rtnValue));
            Double result = tempService.year(yearData);
            Assertions.assertThat(result).isEqualTo(rtnValue);
        }
    }


    @Nested
    @DisplayName("온도 데이터 받아오기(Month) 테스트")
    class tempMonth {
        @Test
        @DisplayName("정상 테스트")
        void test1() {
            int giveYear = 2000;
            double rtnValue = 10.0;
            List<Double> rtnList = new ArrayList<>();

            for (int month = 1; month <= 12; month++) {
                rtnList.add(rtnValue + month);
                lenient().when(tempRepository.findAveTempByYearAndMonth(giveYear, month)).thenReturn(Optional.of(rtnValue + month));
            }

            List<Double> result = tempService.month(giveYear);

            Assertions.assertThat(result).hasSize(12);
            for (int i = 0; i < 12; i++) {
                Assertions.assertThat(result.get(i)).isEqualTo(rtnList.get(i));
            }
        }
    }


    @Nested
    @DisplayName("온도 데이터 받아오기(Day) 테스트")
    class tempDay {
        @Test
        @DisplayName("정상 테스트")
        void test1() {
            int giveYear = 2000;
            int giveMonth = 1;
            List<WeatherData.TempDataOfDay> rtnList = new ArrayList<>();

            rtnList.add(new WeatherData.TempDataOfDay(10));
            rtnList.add(new WeatherData.TempDataOfDay(20));
            rtnList.add(new WeatherData.TempDataOfDay(30));

            when(tempRepository.findAllByYearAndMonth(giveYear, giveMonth)).thenReturn(rtnList);
            List<Double> result = tempService.day(giveYear, giveMonth);

            Assertions.assertThat(result).hasSize(rtnList.size());
            for (int i = 0; i < result.size(); i++) {
                Assertions.assertThat(result.get(i)).isEqualTo((double) rtnList.get(i).temp());
            }
        }
    }
}