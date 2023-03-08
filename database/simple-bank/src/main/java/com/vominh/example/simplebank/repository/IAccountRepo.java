package com.vominh.example.simplebank.repository;

import com.vominh.example.simplebank.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepo extends JpaRepository<AccountEntity, Integer> {
}
