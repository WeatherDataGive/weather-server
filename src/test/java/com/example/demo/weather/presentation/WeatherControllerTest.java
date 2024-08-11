package com.example.demo.weather.presentation;

import com.example.demo.weather.application.WeatherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @MockBean
    WeatherService weatherService;

    @Autowired
    MockMvc mockMvc;
    String globalUrl = "/api/v1/weather";

    @Test
    @DisplayName("온도 데이터 받기 엔드포인트 테스트")
    void getTemp() throws Exception {

        String url = globalUrl + "/temp";

        List<String> identifierList = new ArrayList<>();
        identifierList.add("year");
        identifierList.add("month");

        for(String identifier : identifierList) {
            // give
            String giveIdentifier = "identifier=" + identifier;
            String giveYear = "year=" + "2024";
            String giveData = "?" + giveIdentifier + "&" + giveYear;

            // when, then
            mockMvc.perform(get(url + giveData))
                    .andExpect(status().isOk());
        }
    }

}