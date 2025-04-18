package org.example.dailydriver.repository;

import org.example.dailydriver.model.entity.CarLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarLocationRepository extends JpaRepository<CarLocation, String> {

    List<CarLocation> findAllByCar_IdOrderByCreatedAtDesc(String carId);

    Optional<CarLocation> findFirstByCar_IdOrderByCreatedAtDesc(String carId);

}
