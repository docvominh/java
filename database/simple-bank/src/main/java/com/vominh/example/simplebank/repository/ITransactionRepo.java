package com.vominh.example.simplebank.repository;

import com.vominh.example.simplebank.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionRepo extends JpaRepository<TransactionEntity, Long> {
}
