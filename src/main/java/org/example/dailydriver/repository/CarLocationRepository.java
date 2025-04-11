package org.example.dailydriver.repository;

import org.example.dailydriver.model.entity.CarLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarLocationRepository extends JpaRepository<CarLocation, String> {
}
