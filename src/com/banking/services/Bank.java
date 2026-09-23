package com.banking.services;

import com.banking.exceptions.AccountNotFoundException;
import com.banking.exceptions.InsufficientFundsException;
import com.banking.exceptions.InvalidAmountException;
import com.banking.exceptions.MinimumBalanceViolationException;
import com.banking.models.BankAccount;
import com.banking.models.CurrentAccount;
import com.banking.models.SavingsAccount;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, BankAccount> accounts = new HashMap<>();

    private int nextAccountNumber = 1001;

    private String generateAccountNumber() {
        String accNum = "ACC" + nextAccountNumber;
        nextAccountNumber++;
        return accNum;
    }

    public SavingsAccount createSavingsAccount(String holderName, double initialAmount) {
        String accountNumber = generateAccountNumber();
        SavingsAccount account = new SavingsAccount(accountNumber, holderName, initialAmount);
        accounts.put(accountNumber, account);
        return account;
    }

    public CurrentAccount createCurrentAccount(String holderName, double initialAmount) {
        String accountNumber = generateAccountNumber();
        CurrentAccount account = new CurrentAccount(accountNumber, holderName, initialAmount);
        accounts.put(accountNumber, account);
        return account;
    }

    public BankAccount getAccount(String accountNumber) throws AccountNotFoundException {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account not found for account number: " + accountNumber);
        }
        return account;
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException, InvalidAmountException {
        BankAccount account = getAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InvalidAmountException,
            InsufficientFundsException, MinimumBalanceViolationException {
        BankAccount account = getAccount(accountNumber);
        account.withdraw(amount);
    }

    public void transferMoney(String fromAccount, String toAccount, double amount)
            throws AccountNotFoundException, InsufficientFundsException,
            InvalidAmountException, MinimumBalanceViolationException {
        BankAccount from = getAccount(fromAccount);
        BankAccount to = getAccount(toAccount);
        from.withdraw(amount);
        to.deposit(amount);
    }

    public void printStatement(String accountNumber) throws AccountNotFoundException {
        BankAccount account = getAccount(accountNumber);
        account.printStatement();
    }

    public void displayAllAccounts() {
        for (BankAccount account : accounts.values()) {
            System.out.println(account);
        }
    }

    public void closeAccount(String accountNumber) throws AccountNotFoundException {
        BankAccount account = getAccount(accountNumber);
        accounts.remove(accountNumber);
    }


}
