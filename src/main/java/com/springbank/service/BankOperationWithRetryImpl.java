package com.springbank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import com.springbank.exceptions.AccountCreationException;
import com.springbank.exceptions.AccountTerminationException;
import com.springbank.exceptions.InSufficientFundException;

@Service
public class BankOperationWithRetryImpl implements BankOperation {

	@Autowired
	private RetryTemplate retryTemplate;

	private static Logger log = LoggerFactory.getLogger(BankOperationWithRetryImpl.class);

	@Override
	public String openBankAccount() throws AccountCreationException {
		final RetryCallback<String, AccountCreationException> retryCallback = context -> {
			log.info("opening bank account using retry template.....");
			throw new AccountCreationException("user details not sufficient");
		};

		final RecoveryCallback<String> recoveryCallback = context -> {
			log.info("default fallback executed");
			return "default";
		};

		return retryTemplate.execute(retryCallback, recoveryCallback);
	}

	@Override
	public String withdrawMoney(final Double money) throws InSufficientFundException {
		final RetryCallback<String, InSufficientFundException> retryCallback = context -> {
			log.info("withdrawing cash using retry template.....");
			throw new InSufficientFundException("fund is not sufficient");
		};

		final RecoveryCallback<String> recoveryCallback = context -> {
			log.info("default fallback executed");
			return "default";
		};

		return retryTemplate.execute(retryCallback, recoveryCallback);
	}

	@Override
	public String checkAccountDetails(final String accountId) throws AccountTerminationException {
		final RetryCallback<String, InSufficientFundException> retryCallback = context -> {
			log.info("checking account details using retry template.....");
			throw new AccountTerminationException("account does not exists");
		};

		final RecoveryCallback<String> recoveryCallback = context -> {
			log.info("default fallback executed");
			return "default";
		};

		return retryTemplate.execute(retryCallback, recoveryCallback);
	}

}
