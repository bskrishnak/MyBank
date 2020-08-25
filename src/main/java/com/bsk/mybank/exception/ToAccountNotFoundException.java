package com.bsk.mybank.exception;

public class ToAccountNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ToAccountNotFoundException(String message) {
		super(message);
	}

}
