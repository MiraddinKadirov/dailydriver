package org.example.dailydriver.repository;

import org.example.dailydriver.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    @Query(value = """
                SELECT * FROM comment WHERE car_id = :carId AND deleted = false ORDER BY created_at DESC
            """, nativeQuery = true)
    List<Comment> findAllByCarIdNative(@Param("carId") String carId);


}
