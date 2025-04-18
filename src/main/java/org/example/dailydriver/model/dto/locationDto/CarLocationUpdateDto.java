package org.example.dailydriver.model.dto.locationDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarLocationUpdateDto {

    private String id;
    private String carId;
    private Double latitude;
    private Double longitude;

}
