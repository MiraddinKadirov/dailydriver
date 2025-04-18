package org.example.dailydriver.repository;

import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.CarRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRatingRepository extends CrudRepository<CarRating, String> {

    List<CarRating> findByCar(Car car);

}
