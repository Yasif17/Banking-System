package com.banking.models;

public class CurrentAccount extends BankAccount {

    private static final double MINIMUM_BALANCE_LIMIT = 1000.0;

    public CurrentAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        super(accountNumber, accountHolderName, initialDeposit);
    }

    @Override
    public double getMinimumBalance() {
        return -MINIMUM_BALANCE_LIMIT;
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
