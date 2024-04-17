package com.shopify.api.exceptions;

public class UsernameAlreadyTakenException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameAlreadyTakenException() {
		super();
	}

	public UsernameAlreadyTakenException(String message) {
		super(message);
	}

	public UsernameAlreadyTakenException(String message, Throwable cause) {
		super(message, cause);
	}
}
