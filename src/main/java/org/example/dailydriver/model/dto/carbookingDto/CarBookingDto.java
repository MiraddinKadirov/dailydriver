package org.example.dailydriver.model.dto.carbookingDto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarBookingDto {

    private String carId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
