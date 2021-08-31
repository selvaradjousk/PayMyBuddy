package com.paymybuddy.webapp.exception;

public class BalanceNotSufficientException extends RuntimeException {

    public BalanceNotSufficientException(String message) {
        super(message);
    }
}