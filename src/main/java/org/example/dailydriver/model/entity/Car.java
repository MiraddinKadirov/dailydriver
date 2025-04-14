package org.example.dailydriver.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dailydriver.model.entity.baseEntity.BaseEntity;
import org.example.dailydriver.model.enums.CarColor;
import org.example.dailydriver.model.enums.Category;
import org.example.dailydriver.model.enums.FuelType;
import org.example.dailydriver.model.enums.Steering;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class Car extends BaseEntity {

    private String name;
    private Integer capacity;
    private Double price;
    private String description;
    private LocalDate productionYear;
    private Integer rating;
    private Boolean active = Boolean.TRUE;
    private Boolean isAvailable = Boolean.FALSE;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<File> files;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private CarLocation location;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Steering steering;

    @Enumerated(EnumType.STRING)
    private CarColor color;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

}
