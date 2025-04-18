package org.example.dailydriver.model.dto.locationDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "LocationCreateDto", description = "Mashina joylashuvi")
public class LocationCreateDto {

    private Double latitude;
    private Double longitude;
    private String carId;
}
