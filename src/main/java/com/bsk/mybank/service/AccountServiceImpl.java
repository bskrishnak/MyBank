package com.bsk.mybank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsk.mybank.entity.Account;
import com.bsk.mybank.entity.User;
import com.bsk.mybank.exception.AccountNotFoundException;
import com.bsk.mybank.exception.UserNotFoundException;
import com.bsk.mybank.repository.AccountRepository;
import com.bsk.mybank.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Account getAccountSummary(int userId) throws AccountNotFoundException {
		
		
		Optional<User> user = userRepository.findByUserId(userId);
		if(user.isPresent()) {
			Account account = accountRepository.findAccountByUser(user);
			if(account==null) {
				throw new AccountNotFoundException("account not found");
			}
			return account;
		} else {
			throw new UserNotFoundException("user not found");
		}
		
	}
}