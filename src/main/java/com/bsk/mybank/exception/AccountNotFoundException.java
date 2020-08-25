package com.bsk.mybank.exception;

public class AccountNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		return "account is not found";
	}


}
