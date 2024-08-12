package com.example.demo.weather.application;

import com.example.demo.config.NotFoundDataException;
import com.example.demo.weather.application.implement.TempService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.lenient;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeatherFactoryTest {
    @InjectMocks
    WeatherFactory weatherFactory;

    @Mock
    Map<String, WeatherService> weatherServiceMap;

    String temp = DomainIdentifier.TEMP.getIdentifier();

    @Nested
    @DisplayName("인스턴스 받아오기 테스트")
    class getInstance {

        @BeforeEach
        void befoer() {
            lenient().when(weatherServiceMap.get("tempService")).thenReturn(new TempService(null));
        }

        @Test
        @DisplayName("temp 테스트")
        void test1() {
            WeatherService weatherService = weatherFactory.getInstance(temp);
            assertInstanceOf(TempService.class, weatherService);
        }

        @Test
        @DisplayName("실패 테스트")
        void test2() {
            assertThrows(NotFoundDataException.class, () -> weatherFactory.getInstance("test"));
        }
    }
}