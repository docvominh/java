package com.vominh.example.aspectj;

import com.vominh.example.aspectj.exception.TransactionException;
import com.vominh.example.aspectj.exception.WithdrawException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankAccountTest {

    @Test
    @Order(0)
    void withdrawSuccessTest() {
        BankAccount minh = new BankAccount(1000, "Minh", 1000000L);
        minh.withdraw(500000L);
        Assertions.assertEquals(1000000 - 500000, minh.getBalance());
    }

    @Test
    @Order(1)
    void withdrawFailedTest() {
        BankAccount minh = new BankAccount(1000, "Minh", 1000000L);

        Throwable exception = Assertions.assertThrows(WithdrawException.class, () -> {
            minh.withdraw(5000000L);
        });

        Assertions.assertEquals("Balance not enough !!!", exception.getMessage());
    }

    @Test
    @Order(2)
    void depositTest() {
        BankAccount minh = new BankAccount(1000, "Minh", 1000000L);
        minh.deposit(5000L);
        Assertions.assertEquals(1000000 + 5000, minh.getBalance());
    }

    @Test
    @Order(3)
    void transferFailedTest() {
        BankAccount minh = new BankAccount(1000, "Minh", 1000000L);
        var transaction = new Transaction(minh.getAccountNumber(), 1001, 30000);

        Throwable exception = Assertions.assertThrows(TransactionException.class, () -> {
            minh.transfer(transaction);
        });

        Assertions.assertEquals("Amount invalid, must between 50000 and 1000000", exception.getMessage());
        minh.transfer(transaction, true);
    }

    @Test
    @Order(4)
    void transferSuccessTest() {
        BankAccount minh = new BankAccount(1000, "Minh", 1000000L);
        var transaction = new Transaction(minh.getAccountNumber(), null, 50000);
        minh.transfer(transaction);
        Assertions.assertEquals(950000, minh.getBalance());
    }
}
