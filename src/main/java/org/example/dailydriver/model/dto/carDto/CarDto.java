package org.example.dailydriver.model.dto.carDto;

import jakarta.persistence.*;
import lombok.*;
import org.example.dailydriver.model.entity.CarLocation;
import org.example.dailydriver.model.entity.Comment;
import org.example.dailydriver.model.enums.CarColor;
import org.example.dailydriver.model.enums.Category;
import org.example.dailydriver.model.enums.FuelType;
import org.example.dailydriver.model.enums.Steering;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {

    private String id;
    private String name;
    private Integer capacity;
    private Double price;
    private String description;
    private LocalDateTime productionYear;
    private Integer rating;
    private Boolean active = Boolean.TRUE;
    private Boolean isAvailable = Boolean.FALSE;
    private Set<Comment> comments;
    private CarLocation location;
    private Category category;
    private Steering steering;
    private CarColor color;
    private FuelType fuelType;

}
