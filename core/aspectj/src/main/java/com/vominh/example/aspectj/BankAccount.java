package com.vominh.example.aspectj;

import com.vominh.example.aspectj.annotation.Check;

public class BankAccount {
    private int accountNumber;
    private String accountOwner;
    private long balance;

    public BankAccount(int accountNumber, String accountOwner, long balance) {
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.balance = balance;
    }

    public long withdraw(long amount) {
        balance -= amount;
        return balance;
    }

    public long deposit(long amount) {
        balance += amount;
        return balance;
    }

    @Check
    public long transfer(Transaction transaction) {
        balance -= transaction.getAmount();
        return balance;
    }

    public long transfer(Transaction transaction, boolean forced) {
        balance -= transaction.getAmount();
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
