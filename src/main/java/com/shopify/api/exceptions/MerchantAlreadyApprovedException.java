package com.shopify.api.exceptions;

public class MerchantAlreadyApprovedException extends RuntimeException {
    public MerchantAlreadyApprovedException() {
        super();
    }

    public MerchantAlreadyApprovedException(String message) {
        super(message);
    }

    public MerchantAlreadyApprovedException(String message, Throwable cause) {
        super(message, cause);
    }
}
