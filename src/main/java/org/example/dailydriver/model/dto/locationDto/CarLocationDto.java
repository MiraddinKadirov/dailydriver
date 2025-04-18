package org.example.dailydriver.model.dto.locationDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarLocationDto {

    private String id;
    private Double latitude;
    private Double longitude;
    private LocalDateTime createdAt;

}
