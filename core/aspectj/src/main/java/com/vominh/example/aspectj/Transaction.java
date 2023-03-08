package com.vominh.example.aspectj;

import com.vominh.example.aspectj.annotation.Label;
import com.vominh.example.aspectj.annotation.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Transaction {
    private Integer sourceAccount;

    @NotNull
    private Integer destinationAccount;

//    @Label("Transfer amount")
//    @Range(min = 50000, max = 1000000)
    @Min(50000)
    @Max(1000000)
    private long amount;

    public Transaction(Integer sourceAccount, Integer destinationAccount, long amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public int getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(int sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public int getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(int destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
