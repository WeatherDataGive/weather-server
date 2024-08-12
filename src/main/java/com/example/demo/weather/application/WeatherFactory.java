package com.example.demo.weather.application;

import com.example.demo.config.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class WeatherFactory {
    private final Map<String, WeatherService> weatherServiceMap;
    public WeatherService getInstance(String domain) {
        if(DomainIdentifier.TEMP.getIdentifier().equals(domain)) return this.weatherServiceMap.get("tempService");
        if(DomainIdentifier.TEMPMAX.getIdentifier().equals(domain)) return this.weatherServiceMap.get("testService");
        throw new NotFoundDataException("해당 데이터를 찾을 수 없습니다");
    }
}
