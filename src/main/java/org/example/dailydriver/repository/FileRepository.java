package org.example.dailydriver.repository;

import org.example.dailydriver.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,String> {

    @Query("SELECT f FROM File f WHERE f.car.id = :carId")
    List<File> findAllByCar(@Param("carId") String carId);


}
