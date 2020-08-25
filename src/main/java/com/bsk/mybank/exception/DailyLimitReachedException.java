package com.bsk.mybank.exception;

public class DailyLimitReachedException extends Exception {

	private static final long serialVersionUID = 1L;

	public DailyLimitReachedException(String message) {
		super(message);
	}

}
