package org.example.dailydriver.model.dto.carDto;

import lombok.*;
import org.example.dailydriver.model.entity.CarLocation;
import org.example.dailydriver.model.entity.Comment;
import org.example.dailydriver.model.entity.File;
import org.example.dailydriver.model.enums.CarColor;
import org.example.dailydriver.model.enums.Category;
import org.example.dailydriver.model.enums.FuelType;
import org.example.dailydriver.model.enums.Steering;

import java.time.LocalDate;
import java.util.List;

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
    private LocalDate productionYear;
    private Integer rating;
    private Boolean active = Boolean.TRUE;
    private Boolean isAvailable = Boolean.FALSE;
    private List<Comment> comments;
    private CarLocation location;
    private Category category;
    private Steering steering;
    private CarColor color;
    private FuelType fuelType;
    private List<File> files;


}
