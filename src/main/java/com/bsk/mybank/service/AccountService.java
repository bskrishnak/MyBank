package com.bsk.mybank.service;

import com.bsk.mybank.entity.Account;
import com.bsk.mybank.exception.AccountNotFoundException;

public interface AccountService  {

	

	Account getAccountSummary(int userId) throws AccountNotFoundException;

}
