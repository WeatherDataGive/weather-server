package com.example.demo.weather.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainIdentifier {
    TEMP("temp");
    private final String identifier;
}
