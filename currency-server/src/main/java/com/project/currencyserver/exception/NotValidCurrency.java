package com.project.currencyserver.exception;

public class NotValidCurrency extends RuntimeException {
    public NotValidCurrency(String message) {
        super(message);
    }
}
