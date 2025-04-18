package org.example.dailydriver.model.dto.carDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
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
@Schema(name = "CarCreateDto", description = "Ma'lumotlar JSON ko‘rinishida beriladi")
public class CarCreateDto {

    private String name;
    private Integer capacity;
    private Double price;
    private String description;
    private LocalDate productionYear;
    private Category category;
    private Steering steering;
    private CarColor color;
    private FuelType fuelType;
}
