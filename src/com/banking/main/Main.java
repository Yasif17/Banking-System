package com.banking.main;

import com.banking.exceptions.AccountNotFoundException;
import com.banking.exceptions.InsufficientFundsException;
import com.banking.exceptions.InvalidAmountException;
import com.banking.exceptions.MinimumBalanceViolationException;
import com.banking.services.Bank;


import com.banking.exceptions.*;
import com.banking.models.BankAccount;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("   Banking System Simulation");
        System.out.println("=================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner);

            try {
                switch (choice) {
                    case 1:
                        openSavingsAccount(bank, scanner);
                        break;
                    case 2:
                        openCurrentAccount(bank, scanner);
                        break;
                    case 3:
                        depositFunds(bank, scanner);
                        break;
                    case 4:
                        withdrawFunds(bank, scanner);
                        break;
                    case 5:
                        transferFunds(bank, scanner);
                        break;
                    case 6:
                        checkBalance(bank, scanner);
                        break;
                    case 7:
                        printStatement(bank, scanner);
                        break;
                    case 8:
                        bank.displayAllAccounts();
                        break;
                    case 9:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Please choose a valid option.");
                }
            } catch (AccountNotFoundException | InvalidAmountException
                     | InsufficientFundsException | MinimumBalanceViolationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1: Open Savings Account");
        System.out.println("2: Open Current Account");
        System.out.println("3: Deposit Funds");
        System.out.println("4: Withdraw Funds");
        System.out.println("5: Transfer Funds");
        System.out.println("6: Check Balance");
        System.out.println("7: Print Statement");
        System.out.println("8: Display All Accounts");
        System.out.println("9: Exit");
    }

    private static void openSavingsAccount(Bank bank, Scanner scanner) {
        System.out.println("Enter holder name:");
        String name = scanner.nextLine();
        double deposit = readDouble(scanner, "Enter initial deposit (min 500):");
        var account = bank.createSavingsAccount(name, deposit);
        System.out.println("Created: " + account.getAccountNumber());
    }

    private static void openCurrentAccount(Bank bank, Scanner scanner) {
        System.out.println("Enter holder name:");
        String name = scanner.nextLine();
        double deposit = readDouble(scanner, "Enter initial deposit:");
        var account = bank.createCurrentAccount(name, deposit);
        System.out.println("Created: " + account.getAccountNumber());
    }

    private static void depositFunds(Bank bank, Scanner scanner) throws AccountNotFoundException, InvalidAmountException {
        System.out.println("Enter account number:");
        String accNum = scanner.nextLine();
        double amount = readDouble(scanner, "Enter deposit amount:");
        bank.deposit(accNum, amount);
        System.out.println("Deposit successful.");
    }

    private static void withdrawFunds(Bank bank, Scanner scanner)
            throws AccountNotFoundException, InvalidAmountException, InsufficientFundsException, MinimumBalanceViolationException {
        System.out.println("Enter account number:");
        String accNum = scanner.nextLine();
        double amount = readDouble(scanner, "Enter withdrawal amount:");
        bank.withdraw(accNum, amount);
        System.out.println("Withdrawal successful.");
    }

    private static void transferFunds(Bank bank, Scanner scanner)
            throws AccountNotFoundException, InvalidAmountException, InsufficientFundsException, MinimumBalanceViolationException {
        System.out.println("Enter source account number:");
        String from = scanner.nextLine();
        System.out.println("Enter destination account number:");
        String to = scanner.nextLine();
        double amount = readDouble(scanner, "Enter transfer amount:");
        bank.transferMoney(from, to, amount);
        System.out.println("Transfer successful.");
    }

    private static void checkBalance(Bank bank, Scanner scanner) throws AccountNotFoundException {
        System.out.println("Enter account number:");
        String accNum = scanner.nextLine();
        BankAccount account = bank.getAccount(accNum);
        System.out.println("Balance: " + account.getBalance());
    }

    private static void printStatement(Bank bank, Scanner scanner) throws AccountNotFoundException {
        System.out.println("Enter account number:");
        String accNum = scanner.nextLine();
        bank.printStatement(accNum);
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a whole number.");
                scanner.nextLine();
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}