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

    @Nested
    @DisplayName("날씨 데이터 받기 테스트")
    class getData {

        private final String url = globalUrl + "/temp";
        @Nested
        @DisplayName("엔드포인트 테스트")
        class test1 {
            @Test
            @DisplayName("정상적인 요청 테스트")
            void tes1() throws Exception {

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

            @Nested
            @DisplayName("잘못된 데이터 요청 테스트")
            class test2 {
                @Test
                @DisplayName("잘못된 요청 테스트")
                void tes1() throws Exception {
                    // give`
                    String giveIdentifier = "identifier=" + "dirty";
                    String giveYear = "year=" + "dirty";
                    String giveData = "?" + giveIdentifier + "&" + giveYear;

                    // when, then
                    mockMvc.perform(get(url + giveData))
                            .andExpect(status().isBadRequest());
                }
                @Test
                @DisplayName("잘못된 식별자 요청 테스트")
                void tes2() throws Exception {
                    // give`
                    String giveIdentifier = "identifier=" + "dirty";
                    String giveYear = "year=" + "2024";
                    String giveData = "?" + giveIdentifier + "&" + giveYear;

                    // when, then
                    mockMvc.perform(get(url + giveData))
                            .andExpect(status().isBadRequest());
                }

                @Test
                @DisplayName("잘못된 년도 요청 테스트")
                void tes3() throws Exception {
                    // give`
                    String giveIdentifier = "identifier=" + "year";
                    String giveYear = "year=" + "dirty";
                    String giveData = "?" + giveIdentifier + "&" + giveYear;

                    // when, then
                    mockMvc.perform(get(url + giveData))
                            .andExpect(status().isBadRequest());
                }
            }

            @Test
            @DisplayName("빈 데이터 요청 테스트")
            void tes3() throws Exception {
                mockMvc.perform(get(url))
                        .andExpect(status().isBadRequest());
            }
        }

    }

}