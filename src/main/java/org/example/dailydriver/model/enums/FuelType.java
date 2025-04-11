package org.example.dailydriver.model.enums;

import lombok.Getter;

@Getter
public enum FuelType {

    BENZIN("BENZIN"),
    DIESEL("DIZEL"),
    GAS("GAZ"),
    ELECTRIC("ELEKTRO"),
    HYBRID("GIBRID");

    private final String label;

    FuelType(String label) {
        this.label = label;
    }

}
