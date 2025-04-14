package org.example.dailydriver.repository;

import org.example.dailydriver.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query("SELECT c FROM Car c WHERE c.id = :id AND c.deleted = false")
    Optional<Car> findByIdAndNotDeleted(@Param("id") String id);

    List<Car> findAllByDeletedFalseAndActiveTrue();

    @Query(value = "SELECT * FROM car WHERE id = :id AND deleted = false AND available = false AND active = true", nativeQuery = true)
    List<Car> findAllByDeletedFalseAndAvailableFalseAndActiveTrue();

    @Query(value = "SELECT * FROM car WHERE id = :id AND deleted = false AND available = false AND active = true", nativeQuery = true)
    public Car findByIdAndDeletedFalseAndAvailableFalseAndActiveTrue(@Param("id") String id);


    @Query("SELECT c FROM Car c LEFT JOIN c.comments com GROUP BY c.id ORDER BY COUNT(com) DESC")
    List<Car> findTopCommentedCars();

}
