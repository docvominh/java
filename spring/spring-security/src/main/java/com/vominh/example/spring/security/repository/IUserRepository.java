package com.vominh.example.spring.security.repository;

import com.vominh.example.spring.security.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, String> {
    @Query(value = "SELECT u FROM UserEntity u WHERE lower(u.fullName) LIKE lower(CONCAT('%',:fullName,'%'))")
    List<UserEntity> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    Optional<UserEntity> findByUserNameOrEmail(@Param("userName") String userName, @Param("email") String email);
}
