/**
 * 
 */
package com.bsk.mybank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bsk.mybank.config.ApplicationConstants;
import com.bsk.mybank.dto.TransactionRequestDto;
import com.bsk.mybank.dto.TransactionResponseDto;
import com.bsk.mybank.entity.Account;
import com.bsk.mybank.entity.Beneficiary;
import com.bsk.mybank.entity.Transaction;
import com.bsk.mybank.entity.User;
import com.bsk.mybank.exception.BeneficiaryNotFoundException;
import com.bsk.mybank.exception.DailyLimitReachedException;
import com.bsk.mybank.exception.FromAccountNotFoundException;
import com.bsk.mybank.exception.ToAccountNotFoundException;
import com.bsk.mybank.repository.AccountRepository;
import com.bsk.mybank.repository.BeneficiaryRepository;
import com.bsk.mybank.repository.TransactionRepository;

/**
 * @author Bala
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	private static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public TransactionResponseDto fundTransfer(TransactionRequestDto transactionRequestDto)
			throws FromAccountNotFoundException, ToAccountNotFoundException, BeneficiaryNotFoundException,
			DailyLimitReachedException {

		logger.info("Inside fund transfer method: ");

		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();

		Long fromAccountNumber = transactionRequestDto.getFromAccountNumber();
		Long toAccountNumber = transactionRequestDto.getToAccountNumber();
		Double transferAmount = transactionRequestDto.getTransferAmount();
		Double balance = 0.0;
		Double amount = 0.0;
		Optional<User> users = accountRepository.findUserByAccountNumber(fromAccountNumber);
		if (users.isPresent()) {
			User user = users.get();
			Optional<Account> fromAccounts = accountRepository.findByAccountNumber(fromAccountNumber);
			if (fromAccounts.isPresent()) {
				Account fromAccount = fromAccounts.get();
				balance = fromAccount.getAvailableBalance();

				Optional<List<Beneficiary>> beneficiaries = beneficiaryRepository.findByUser(user);
				if (beneficiaries.isPresent()) {
					List<Beneficiary> beneficiaryList = beneficiaries.get();

					Optional<Account> toAccounts = accountRepository.findByAccountNumber(toAccountNumber);
					if (toAccounts.isPresent()) {
						Account toAccount = toAccounts.get();
						Optional<Beneficiary> beneficiary = beneficiaryRepository.findByBeneficiaryAccount(toAccount);
						if (beneficiary.isPresent()) {
							Beneficiary beneficiaryAccount = beneficiary.get();

							if (beneficiaryList.contains(beneficiaryAccount)) {
								Double sourceBalance = balance;
								Double destinationBalance = toAccount.getAvailableBalance();
								if (sourceBalance < transferAmount | transferAmount < 0) {
									logger.info("transaction failed");
									transactionResponseDto.setStatusMessage(ApplicationConstants.TRANSACTION_FAILED);
									transactionResponseDto.setStatusCode(HttpStatus.OK.value());
									return transactionResponseDto;

								} else {

									Optional<List<Transaction>> transactions = transactionRepository
											.findByTransactionDate(LocalDate.now());
									List<Transaction> transactionList = transactions.get();

									for (Transaction transaction : transactionList) {
										amount = amount + transaction.getTransactionAmount();
									}
									if (fromAccount.getAccountType().equals("saving") & amount >= 50000
											| amount + transactionRequestDto.getTransferAmount() >= 50000) {
										throw new DailyLimitReachedException(ApplicationConstants.DAY_LIMIT_REACHED);
									} else {
										sourceBalance = sourceBalance - transferAmount;
										destinationBalance = destinationBalance + transferAmount;
										fromAccount.setAvailableBalance(sourceBalance);
										accountRepository.save(fromAccount);
										toAccount.setAvailableBalance(destinationBalance);
										accountRepository.save(toAccount);
										Transaction transaction = new Transaction();
										transaction.setFromAccountNumber(fromAccount.getAccountNumber());
										transaction.setToAccountNumber(toAccount.getAccountNumber());
										transaction.setTransactionAmount(transferAmount);
										transaction.setTransactionDate(LocalDate.now());
										transactionRepository.save(transaction);
									}
								}

							} else {
								throw new BeneficiaryNotFoundException(ApplicationConstants.BENEFICIARY_NOT_FOUND);
							}

						}

					}
				}

			} else {
				throw new FromAccountNotFoundException(ApplicationConstants.FROM_ACCOUNT_NOT_FOUND);
			}

		}

		return transactionResponseDto;
	}
}