package com.shopify.api.exceptions;

public class BankInfoAlreadyExistException extends  RuntimeException{
    public BankInfoAlreadyExistException() {
        super();
    }

    public BankInfoAlreadyExistException(String message) {
        super(message);
    }

    public BankInfoAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
