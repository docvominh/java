package com.vominh.example.simplebank.repository;

import com.vominh.example.simplebank.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepo extends JpaRepository<CustomerEntity, Integer> {
}
