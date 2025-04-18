package org.example.dailydriver.repository;

import jakarta.transaction.Transactional;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query("SELECT c FROM Car c WHERE c.id = :id AND c.deleted = false")
    Optional<Car> findByIdAndNotDeleted(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("UPDATE Car c SET c.active = :active WHERE c.id = :id")
    void updateCarActiveStatus(@Param("id") String id, @Param("active") boolean active);

    @Query("SELECT c FROM Car c WHERE c.active = true AND c.isAvailable = false AND c.deleted = false")
    Page<Car> findAllActiveUnavailableNotDeleted(Pageable pageable);

    @Query("""
    SELECT c FROM Car c
    LEFT JOIN c.comments cm
    WHERE c.deleted = false
    GROUP BY c
    ORDER BY c.rating DESC, COUNT(cm) DESC
""")
    List<Car> findPopularCars();


    @Query("SELECT c FROM Car c WHERE c.rating = :rating AND c.deleted = false")
    List<Car> findByRating(@Param("rating") Double rating);

    @Query("SELECT c FROM Car c LEFT JOIN c.comments com GROUP BY c.id ORDER BY COUNT(com) DESC")
    List<Car> findTopCommentedCars();

    @Query("SELECT c FROM Car c WHERE c.deleted = false")
    List<Car> findAllAndNotDeleted();

    @Query("SELECT COUNT(c) FROM Car c WHERE c.deleted = false")
    Long countAllByNotDeleted();

    @Query("SELECT c.category, COUNT(c) FROM Car c WHERE c.deleted = false GROUP BY c.category")
    List<Object[]> countByCategory();

    @Query("SELECT COUNT(c) FROM Car c WHERE c.deleted = false AND c.isAvailable = false")
    Long countAllByNotAvailable();

    @Query("SELECT COUNT(c) FROM Car c WHERE c.deleted = false AND c.active = true")
    Long countAllByActive();

    List<Car> findAllByCategoryAndDeletedFalse(Category category);

    @Query("SELECT AVG(c.rating) FROM Car c WHERE c.deleted = false")
    Double getAverageRating();

    @Query("SELECT YEAR(c.productionYear), COUNT(c) FROM Car c WHERE c.deleted = false GROUP BY YEAR(c.productionYear)")
    List<Object[]> countByProductionYear();



}
