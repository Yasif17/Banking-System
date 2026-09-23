package com.banking.models;

public class SavingsAccount extends BankAccount {

    private static final double MINIMUM_BALANCE = 500.0;

    public SavingsAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        super(accountNumber, accountHolderName, initialDeposit);
    }

    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
