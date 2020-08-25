package com.bsk.mybank.service;

import com.bsk.mybank.dto.TransactionRequestDto;
import com.bsk.mybank.dto.TransactionResponseDto;
import com.bsk.mybank.exception.BeneficiaryNotFoundException;
import com.bsk.mybank.exception.DailyLimitReachedException;
import com.bsk.mybank.exception.FromAccountNotFoundException;
import com.bsk.mybank.exception.ToAccountNotFoundException;

public interface TransactionService {
	
	public TransactionResponseDto fundTransfer(TransactionRequestDto transactionRequestDto)throws FromAccountNotFoundException, ToAccountNotFoundException, BeneficiaryNotFoundException, DailyLimitReachedException;

}
