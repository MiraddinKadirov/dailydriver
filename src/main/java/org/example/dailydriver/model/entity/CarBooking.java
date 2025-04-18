package org.example.dailydriver.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dailydriver.model.entity.baseEntity.Identity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBooking extends Identity {


    @ManyToOne
    private Car car;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private boolean active = true;

}
