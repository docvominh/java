package com.vominh.example.simplebank.it;

import com.vominh.example.simplebank.repository.IAccountRepo;
import com.vominh.example.simplebank.repository.ITransactionRepo;
import com.vominh.example.simplebank.request.WithDrawRequest;
import com.vominh.example.simplebank.service.TransactionService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionIT {

    @Autowired
    private IAccountRepo accountRepo;

    @Autowired
    private ITransactionRepo transactionRepo;

    @Autowired
    private TransactionService transactionService;

    @BeforeAll
    void setUp() {
        transactionRepo.deleteAll();
    }

    @Test
    @Order(0)
    void withdrawTest() {
        var account = accountRepo.findAll().get(0);
        var currentBalance = account.getBalance();

        var request = WithDrawRequest.builder()
                .accountId(account.getAccountId())
                .amount(5000)
                .executeEmployee(null)
                .atmAddress("382 Nui Thanh")
                .build();
        var status = transactionService.withDraw(request);
        account = accountRepo.findById(account.getAccountId()).get();
        Assertions.assertEquals(currentBalance - 5000, account.getBalance());
    }

    @Test
    @Order(1)
    void sequenceWithdrawTest() {
        var test = accountRepo.findAll(Sort.by("accountId")).get(0);
        test.setBalance(5000000L);
        accountRepo.save(test);
        var currentBalance = test.getBalance();

        for (int i = 0; i < 10; i++) {
            var account = accountRepo.findAll(Sort.by("accountId")).get(0);

            var request = WithDrawRequest.builder()
                    .accountId(account.getAccountId())
                    .amount(5000)
                    .executeEmployee(null)
                    .atmAddress("382 Nui Thanh")
                    .build();

            transactionService.withDraw(request);
        }

        test = accountRepo.findById(test.getAccountId()).get();
        Assertions.assertEquals(currentBalance - 50000, test.getBalance());
    }

    @Test
    @Order(2)
    void parallelWithdrawTest() {
        var test = accountRepo.findAll(Sort.by("accountId")).get(0);
        test.setBalance(5000000L);
        accountRepo.save(test);
        var currentBalance = test.getBalance();

        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(() -> {
                var account = accountRepo.findAll(Sort.by("accountId")).get(0);

                var request = WithDrawRequest.builder()
                        .accountId(account.getAccountId())
                        .amount(5000)
                        .executeEmployee(null)
                        .atmAddress("382 Nui Thanh")
                        .build();
                transactionService.withDraw(request);
            });
        }

        test = accountRepo.findById(test.getAccountId()).get();
        Assertions.assertEquals(currentBalance - 50000, test.getBalance());
    }
}
