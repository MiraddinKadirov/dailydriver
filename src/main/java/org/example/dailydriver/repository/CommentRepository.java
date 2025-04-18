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

    @Query("SELECT c FROM Comment c WHERE c.id = :id")
    Comment findByCarId(@Param("id") String id);

    @Query(value = "SELECT * FROM Comment  WHERE car_id = :id", nativeQuery = true)
    List<Comment> findAllByCarId(@Param("id") String id);





}
