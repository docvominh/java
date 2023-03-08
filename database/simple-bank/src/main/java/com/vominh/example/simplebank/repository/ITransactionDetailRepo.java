package com.vominh.example.simplebank.repository;

import com.vominh.example.simplebank.entity.TransactionDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionDetailRepo extends JpaRepository<TransactionDetailEntity, Integer> {
}
