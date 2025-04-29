package org.example.dailydriver.repository;

import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.CarRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRatingRepository extends CrudRepository<CarRating, String> {


    // carId orqali reytinglar ro'yxatini olish
    List<CarRating> findByCar_Id(String carId);

    // Agar kerak bo'lsa, userId bilan ham filtrlangan variant
    Optional<CarRating> findByCar_IdAndUser_Id(String carId, String userId);

    List<CarRating> findByCar(Car car);

}
