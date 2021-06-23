package com.springbank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.springbank.exceptions.AccountCreationException;
import com.springbank.exceptions.AccountTerminationException;
import com.springbank.exceptions.InSufficientFundException;

@Service
public class BankOperationImpl implements BankOperation {

	private static Logger log = LoggerFactory.getLogger(BankOperationImpl.class);

	@Override
	@Retryable(stateful = false, maxAttempts = 3, backoff = @Backoff(delay = 1000), value = {
			AccountCreationException.class })
	public String openBankAccount() throws AccountCreationException {
		log.info("opening bank account.....");
		throw new AccountCreationException("user details not sufficient");
	}

	@Override
	@Retryable(stateful = false, maxAttempts = 2, backoff = @Backoff(delay = 1000), value = {
			InSufficientFundException.class })
	public String withdrawMoney(final Double money) throws InSufficientFundException {
		log.info("withdrawing cash.....");
		throw new InSufficientFundException("fund is not sufficient");
	}

	@Override
	@Retryable(stateful = false, maxAttempts = 1, backoff = @Backoff(delay = 1000), value = {
			AccountTerminationException.class })
	public String checkAccountDetails(final String accountId) throws AccountTerminationException {
		log.info("checking account details.....");
		throw new AccountTerminationException("account does not exists");
	}

}
