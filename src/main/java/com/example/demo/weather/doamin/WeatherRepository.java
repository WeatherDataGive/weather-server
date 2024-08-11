package com.example.demo.weather.doamin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    @Query("SELECT AVG(w.temp) FROM Weather w WHERE w.year = :year")
    Optional<Double> findAveByYear(@Param("year") int year);

    @Query("SELECT AVG(w.temp) FROM Weather w WHERE w.year = :year AND w.month = :month")
    Optional<Double> findAveByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
