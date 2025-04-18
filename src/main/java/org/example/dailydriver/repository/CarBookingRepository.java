package org.example.dailydriver.repository;

import org.example.dailydriver.model.entity.CarBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarBookingRepository extends JpaRepository<CarBooking, String> {

    @Query("SELECT cb FROM CarBooking cb WHERE cb.car.id = :carId AND cb.active = true AND cb.endTime > CURRENT_TIMESTAMP")
    List<CarBooking> findActiveBookingsByCarId(String carId);

}
