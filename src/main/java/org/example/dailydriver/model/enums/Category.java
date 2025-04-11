package org.example.dailydriver.model.enums;

import lombok.Getter;

@Getter
public enum Category {

    SEDAN("SEDAN"),
    SUV("SUV"),
    COUPE("Coupe"),
    HATCHBACK("Hatchback"),
    CONVERTIBLE("Convertible"),
    TRUCK("Yuk mashinasi"),
    VAN("Van"),
    MINIVAN("Minivan"),
    CROSSOVER("Crossover"),
    WAGON("Wagon"),
    PICKUP("Pickup");

    private final String label;

    Category(String label) {
        this.label = label;
    }
}
