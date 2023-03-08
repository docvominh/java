package com.vominh.example.aspectj.aspect;

import com.vominh.example.aspectj.BankAccount;
import com.vominh.example.aspectj.exception.WithdrawException;

public aspect WithdrawAspect {

    boolean result;

    pointcut callWithDraw(long amount, BankAccount bankAccount): call(long com.vominh.example.aspectj.BankAccount.withdraw(long)) && args(amount) && target(bankAccount);

    before(long amount, BankAccount bankAccount): callWithDraw(amount, bankAccount) {
        System.out.println(String.format("%s withdraw amount: %s", bankAccount.getAccountOwner(), amount));
    }

    long around(long amount, BankAccount bankAccount): callWithDraw(amount, bankAccount) {
        System.out.println(String.format("Current balance: %s", bankAccount.getBalance()));
        if (bankAccount.getBalance() < amount) {
            throw new WithdrawException("Balance not enough !!!");
        }
        result = true;
        return proceed(amount, bankAccount);
    }

    after(long amount, BankAccount bankAccount): callWithDraw(amount, bankAccount) {
        if (result) {
            System.out.println("Withdraw success");
        } else {
            System.out.println("Withdraw failed");
        }
    }
}
