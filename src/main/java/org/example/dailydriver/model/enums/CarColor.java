package org.example.dailydriver.model.enums;

import lombok.Getter;

@Getter
public enum CarColor {

    WHITE("White"),
    BLACK("Black"),
    SILVER("Silver"),
    GRAY("Gray"),
    RED("Red"),
    BLUE("Blue"),
    GREEN("Green"),
    YELLOW("Yellow"),
    ORANGE("Orange"),
    BROWN("Brown"),
    BEIGE("Beige"),
    GOLD("Gold"),
    PURPLE("Purple"),
    PINK("Pink"),
    MAROON("Maroon");

    private final String label;

    CarColor(String label) {
        this.label = label;
    }
}
