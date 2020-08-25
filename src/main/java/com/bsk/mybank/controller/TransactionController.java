package com.bsk.mybank.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsk.mybank.dto.TransactionRequestDto;
import com.bsk.mybank.dto.TransactionResponseDto;
import com.bsk.mybank.exception.BeneficiaryNotFoundException;
import com.bsk.mybank.exception.DailyLimitReachedException;
import com.bsk.mybank.exception.FromAccountNotFoundException;
import com.bsk.mybank.exception.ToAccountNotFoundException;
import com.bsk.mybank.service.TransactionService;

@RestController
@RequestMapping("/")
public class TransactionController {
	
	private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;

	public ResponseEntity<TransactionResponseDto> fundTransfer(
			@Valid @RequestBody TransactionRequestDto transactionRequestDto)
			throws FromAccountNotFoundException, ToAccountNotFoundException, BeneficiaryNotFoundException, DailyLimitReachedException {
		logger.info("inside controller fund transfer method: ");
		TransactionResponseDto transactionResponseDto;
		transactionResponseDto = transactionService.fundTransfer(transactionRequestDto);
		return new ResponseEntity<>(transactionResponseDto, HttpStatus.OK);

	}
}
