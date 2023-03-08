package com.vominh.example.spring.rest.repository;

import com.vominh.example.spring.rest.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends JpaRepository<ProductEntity, Integer> {
}
