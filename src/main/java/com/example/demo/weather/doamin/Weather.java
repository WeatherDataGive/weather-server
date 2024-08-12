package com.example.demo.weather.doamin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "weather_table")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer temp;
    private Integer temp_max;
    private Integer temp_min;
    private Integer precipitation;
    private Integer wind_speed;
    private Integer humidity;
}
