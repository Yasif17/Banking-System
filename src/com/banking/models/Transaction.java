package com.banking.models;

import com.banking.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {

    private final LocalDateTime timestamp;
    private final TransactionType type;
    private final double amount;
    private final String description;
    private final double balanceAfter;

    public Transaction(TransactionType type,double amount,double balanceAfter,String description){
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.balanceAfter = balanceAfter;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    @Override
    public String toString() {
        return timestamp + " | " + type + " | Amount: " + amount + " | Balance After: " + balanceAfter + " | " + description;
    }


}
