package org.example.dailydriver.model.dto.carDto;

import lombok.*;
import org.example.dailydriver.model.entity.CarLocation;
import org.example.dailydriver.model.enums.CarColor;
import org.example.dailydriver.model.enums.Category;
import org.example.dailydriver.model.enums.FuelType;
import org.example.dailydriver.model.enums.Steering;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarUpdateDto {

    private String id;
    private String name;
    private Integer capacity;
    private Double price;
    private String description;
    private LocalDate productionYear;
    private Boolean active = Boolean.TRUE;
    private Category category;
    private Steering steering;
    private CarColor color;
    private FuelType fuelType;

}
