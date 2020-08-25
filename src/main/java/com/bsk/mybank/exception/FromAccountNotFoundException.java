package com.bsk.mybank.exception;

public class FromAccountNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public FromAccountNotFoundException(String message) {
		super(message);
	}

}
