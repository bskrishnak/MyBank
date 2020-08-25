package com.bsk.mybank.exception;

public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return "account is not found";
	}

}
