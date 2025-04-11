package org.example.dailydriver.model.enums;

import lombok.Getter;

@Getter
public enum Steering {

    AUTOMATIC("AUTOMATIC"),
    MEXANIK("MEXANIK");

    private final String name;

    Steering(String name) {
        this.name = name;
    }
}
