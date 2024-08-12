package com.example.demo.weather.doamin.implement;

import com.example.demo.weather.doamin.Weather;
import com.example.demo.weather.doamin.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TempRepository extends JpaRepository<Weather, Integer> {
    @Query("SELECT AVG(w.temp) FROM Weather w WHERE w.year = :year")
    Optional<Double> findAveTempByYear(@Param("year") int year);

    @Query("SELECT AVG(w.temp) FROM Weather w WHERE w.year = :year AND w.month = :month")
    Optional<Double> findAveTempByYearAndMonth(@Param("year") int year, @Param("month") int month);

    List<WeatherData.TempDataOfDay> findAllByYearAndMonth(int year, int month);
}
