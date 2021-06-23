package com.springbank.service;

import org.springframework.retry.annotation.Recover;

import com.springbank.exceptions.AccountCreationException;
import com.springbank.exceptions.AccountTerminationException;
import com.springbank.exceptions.InSufficientFundException;

public interface BankOperation {

	String openBankAccount() throws AccountCreationException;

	String withdrawMoney(Double money) throws InSufficientFundException;

	String checkAccountDetails(String accountId) throws AccountTerminationException;

	@Recover
	default String defaultFallback() {
		return "default fallback executed";
	}

}
