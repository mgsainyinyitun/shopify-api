package com.shopify.api.exceptions;

public class BalanceInsufficientException extends  RuntimeException{

    public BalanceInsufficientException() {
        super();
    }

    public BalanceInsufficientException(String message) {
        super(message);
    }

    public BalanceInsufficientException(String message, Throwable cause) {
        super(message, cause);
    }
}

