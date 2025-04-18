package org.example.dailydriver.repository;

import org.example.dailydriver.model.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

    @Query("SELECT a FROM AuthUser a WHERE a.id = :id AND a.deleted = false")
    Optional<AuthUser> findByIdAndNotDeleted(@Param("id") String id);

    Optional<List<AuthUser>> findAllByDeletedFalse();

    boolean existsByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT a FROM AuthUser a WHERE a.username = :username ")
    Optional<AuthUser> findByUsername(String username);
}
