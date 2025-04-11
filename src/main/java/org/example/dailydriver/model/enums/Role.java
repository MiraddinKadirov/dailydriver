package org.example.dailydriver.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    SUPER_ADMIN("SUPER_ADMIN"),
    ADMIN("ADMIN"),
    USER("USER");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
