package com.springbank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbank.service.BankOperation;

@RestController
@RequestMapping("/api/retry/template/operations")
public class BankOperationWithRestTemplateController {

	@Autowired
	private BankOperation bankOperationWithRetryImpl;

	@PostMapping
	public String createUserAccount() {
		return bankOperationWithRetryImpl.openBankAccount();
	}

	@GetMapping("{accountId}")
	public String fetchAccountDetails(@PathVariable("accountId") String accountId) {
		return bankOperationWithRetryImpl.checkAccountDetails(accountId);
	}

	@PostMapping("withdrawCash")
	public String cashWithdrawal(@RequestParam("amount") Double amount) {
		return bankOperationWithRetryImpl.withdrawMoney(amount);
	}

}
