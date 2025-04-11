package org.example.dailydriver.model.enums;

import lombok.Getter;

@Getter
public enum Status {

    REGISTERED("Registered"),
    NOT_REGISTERED("Not Registered"),
    BLOCKED("Blocked");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
