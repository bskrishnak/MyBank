package com.bsk.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsk.mybank.entity.Account;
import com.bsk.mybank.exception.AccountNotFoundException;
import com.bsk.mybank.service.AccountService;


@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<Account> getAccountSummary(@PathVariable int userId) throws AccountNotFoundException {
		Account account = accountService.getAccountSummary(userId);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	

}
