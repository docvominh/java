package com.vominh.example.simplebank.repository;

import com.vominh.example.simplebank.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {
}
