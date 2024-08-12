package com.example.demo.weather.application;

import com.example.demo.weather.doamin.Weather;
import com.example.demo.weather.doamin.WeatherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @InjectMocks
    WeatherServiceImpl weatherServiceImpl;

    @Mock
    WeatherRepository weatherRepository;
    private final int teatYear = 2010;
    double yearAug;


    @BeforeEach
    void before() {
        List<Weather> weathersList = new ArrayList<>();
        int sum = 0;

        weathersList.add(Weather.builder()
                .temp(10)
                .build());
        sum += 10;

        weathersList.add(Weather.builder()
                .temp(20)
                .build());
        sum += 20;

        yearAug = (double) (sum * 12) / (2 * 12);

        for(int i = 1; i <= 12; i++) {
            for(int j = 1; j <= 30; j++) {
                when(weatherRepository.findAllByYearAndMonth(teatYear, i)).thenReturn(weathersList);
            }
        }
    }

    @Nested
    @DisplayName("온도 데이터 받아오기(Year) 테스트")
    class tempYear {
        @Test
        @DisplayName("정상 테스트")
        void test1() {
            Double result = weatherServiceImpl.year(teatYear);
            Assertions.assertThat(result).isEqualTo(yearAug);
        }
    }


//    @Nested
//    @DisplayName("온도 데이터 받아오기(Month) 테스트")
//    class tempMonth {
//        @Test
//        @DisplayName("정상 테스트")
//        void test1() {
//            int giveYear = 2000;
//            double rtnValue = 10.0;
//            List<Double> rtnList = new ArrayList<>();
//
//            for (int month = 1; month <= 12; month++) {
//                rtnList.add(rtnValue + month);
//                lenient().when(weatherRepository.findAveTempByYearAndMonth(giveYear, month)).thenReturn(Optional.of(rtnValue + month));
//            }
//
//            List<Double> result = tempService.month(giveYear);
//
//            Assertions.assertThat(result).hasSize(12);
//            for (int i = 0; i < 12; i++) {
//                Assertions.assertThat(result.get(i)).isEqualTo(rtnList.get(i));
//            }
//        }
//    }


//    @Nested
//    @DisplayName("온도 데이터 받아오기(Day) 테스트")
//    class tempDay {
//        @Test
//        @DisplayName("정상 테스트")
//        void test1() {
//            int giveYear = 2000;
//            int giveMonth = 1;
//            List<Weather> rtnList = new ArrayList<>();
//
//            rtnList.add(Weather.builder()
//                    .temp(10)
//                    .build());
//            rtnList.add(Weather.builder()
//                    .temp(20)
//                    .build());
//            rtnList.add(Weather.builder()
//                    .temp(20)
//                    .build());
//
//            when(weatherRepository.findAllByYearAndMonth(giveYear, giveMonth)).thenReturn(rtnList);
//            List<Double> result = tempService.day(giveYear, giveMonth);
//
//            Assertions.assertThat(result).hasSize(rtnList.size());
//            for (int i = 0; i < result.size(); i++) {
//                Assertions.assertThat(result.get(i)).isEqualTo((double) rtnList.get(i).temp());
//            }
//        }
//    }
}