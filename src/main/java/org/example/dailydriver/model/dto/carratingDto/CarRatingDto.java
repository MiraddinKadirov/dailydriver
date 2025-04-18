package org.example.dailydriver.model.dto.carratingDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRatingDto {

    private String userId;
    private String carId;
    @Min(1)
    @Max(5)
    private Integer rating;

}



