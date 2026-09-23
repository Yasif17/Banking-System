package com.banking.models;

import com.banking.enums.TransactionType;
import com.banking.exceptions.InvalidAmountException;
import com.banking.exceptions.InsufficientFundsException;
import com.banking.exceptions.MinimumBalanceViolationException;

import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {

    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;
    protected List<Transaction> transactionHistory;


    protected BankAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
    }


    public abstract double getMinimumBalance();

    public abstract String getAccountType();


    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive. Received: " + amount);
        }

        balance = balance + amount;
        transactionHistory.add(new Transaction(TransactionType.DEPOSIT, amount, balance, "Deposit"));

    }

    public void withdraw(double amount)
            throws InvalidAmountException,
            InsufficientFundsException,
            MinimumBalanceViolationException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive. Received: " + amount);
        }

        double balanceAfter = balance - amount;

        if (balanceAfter < getMinimumBalance()) {
            if (getMinimumBalance() > 0) {
                throw new MinimumBalanceViolationException(
                        "Insufficient balance to withdraw. " +
                                "Required minimum balance: " + getMinimumBalance());
            } else {
                throw new InsufficientFundsException(
                        "Insufficient funds. Available balance : " + (balance - getMinimumBalance()) + ", Requested: " + amount);
            }
        }

        balance = balance - amount;
        transactionHistory.add(new Transaction(TransactionType.WITHDRAWAL, amount, balance, "Withdrawal"));


    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }


    public void printStatement() {
        System.out.println("Statement for " + accountNumber + " (" + getAccountType() + ")");
        for (Transaction t : transactionHistory) {
            System.out.println(t);
        }
        System.out.println("Current Balance: " + balance);
    }

    @Override
    public String toString() {
        return "[" + accountNumber + "] " + getAccountType() + " - " + accountHolderName + " - Balance: " + balance;
    }


}
