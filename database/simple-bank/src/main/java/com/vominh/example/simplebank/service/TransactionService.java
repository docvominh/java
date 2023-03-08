package com.vominh.example.simplebank.service;

import com.vominh.example.simplebank.TransactionStatus;
import com.vominh.example.simplebank.entity.TransactionDetailEntity;
import com.vominh.example.simplebank.entity.TransactionEntity;
import com.vominh.example.simplebank.enums.TransactionType;
import com.vominh.example.simplebank.repository.IAccountRepo;
import com.vominh.example.simplebank.repository.ITransactionDetailRepo;
import com.vominh.example.simplebank.repository.ITransactionRepo;
import com.vominh.example.simplebank.request.WithDrawRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final IAccountRepo accountRepo;
    private final ITransactionRepo transactionRepo;
    private final ITransactionDetailRepo transactionDetailRepo;

    public TransactionService(IAccountRepo accountRepo, ITransactionRepo transactionRepo, ITransactionDetailRepo transactionDetailRepo) {

        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
        this.transactionDetailRepo = transactionDetailRepo;
    }

    public TransactionEntity getTransaction(long transactionId) {
        return transactionRepo.findById(transactionId).get();
    }

    public List<TransactionEntity> findAll() {
        return transactionRepo.findAll();
    }

    @Transactional
    public TransactionStatus withDraw(WithDrawRequest request) {
        var account = accountRepo.findById(request.getAccountId()).get();
        if (account.getBalance() < request.getAmount()) {
            throw new RuntimeException("Not enough balance");
        }

        TransactionEntity transaction = new TransactionEntity();
        transaction.setSourceAccount(account);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setAmount(request.getAmount());


        transaction.setStatus(TransactionStatus.REGISTERED);

        try {
            transaction = transactionRepo.save(transaction);

            TransactionDetailEntity transactionDetail = new TransactionDetailEntity();
            transactionDetail.setTransaction(transaction);
            if (request.getExecuteEmployee() != null) {
                transactionDetail.setExecuteEmployeeId(request.getExecuteEmployee());
            } else {
                transactionDetail.setAtmAddress(request.getAtmAddress());
            }

            transactionDetailRepo.save(transactionDetail);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return transaction.getStatus();
        }

        try {
            account.setBalance(account.getBalance() - request.getAmount());
            accountRepo.save(account);
            transaction.setStatus(TransactionStatus.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage());
            transaction.setStatus(TransactionStatus.FAILED);
            return transaction.getStatus();
        }

        transactionRepo.save(transaction);
        return transaction.getStatus();

    }

    @Transactional
    public void deposit(int accountId, long amount, Integer executeEmployee) {

    }

    @Transactional
    public void transfer(int sourceAccountId, int destinationAccountId, long amount, Integer executeEmployee) {

    }

}
