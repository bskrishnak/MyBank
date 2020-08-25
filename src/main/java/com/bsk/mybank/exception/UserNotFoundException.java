package com.bsk.mybank.exception;

public class UserNotFoundException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		return "user is not found";
	}

}


