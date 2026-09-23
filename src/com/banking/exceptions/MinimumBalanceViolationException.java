package com.banking.exceptions;

public class MinimumBalanceViolationException extends Exception {
    public MinimumBalanceViolationException(String message) {
        super(message);
    }
}
