package com.bsk.mybank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(
			AccountNotFoundException accountNotFoundException, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(accountNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND.value(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FromAccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(
			FromAccountNotFoundException fromAccountNotFoundException, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(fromAccountNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND.value(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ToAccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(
			ToAccountNotFoundException toAccountNotFoundException, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(toAccountNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND.value(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DailyLimitReachedException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(
			DailyLimitReachedException dailyLimitReachedException, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(dailyLimitReachedException.getMessage(),
				HttpStatus.NOT_FOUND.value(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	

}
