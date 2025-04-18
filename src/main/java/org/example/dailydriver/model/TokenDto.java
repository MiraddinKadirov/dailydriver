package org.example.dailydriver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class TokenDto {

    private String token;
    private Date expiry;

}
