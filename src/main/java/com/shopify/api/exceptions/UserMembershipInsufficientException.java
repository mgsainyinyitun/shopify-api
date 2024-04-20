package com.shopify.api.exceptions;

public class UserMembershipInsufficientException extends  RuntimeException{
    public UserMembershipInsufficientException() {
        super();
    }

    public UserMembershipInsufficientException(String message) {
        super(message);
    }

    public UserMembershipInsufficientException(String message, Throwable cause) {
        super(message, cause);
    }
}
