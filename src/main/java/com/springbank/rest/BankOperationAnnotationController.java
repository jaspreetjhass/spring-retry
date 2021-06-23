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
@RequestMapping("/api/retry/operations")
public class BankOperationAnnotationController {

	@Autowired
	private BankOperation bankOperationImpl;

	@PostMapping
	public String createUserAccount() {
		return bankOperationImpl.openBankAccount();
	}

	@GetMapping("{accountId}")
	public String fetchAccountDetails(@PathVariable("accountId") String accountId) {
		return bankOperationImpl.checkAccountDetails(accountId);
	}

	@PostMapping("withdrawCash")
	public String cashWithdrawal(@RequestParam("amount") Double amount) {
		return bankOperationImpl.withdrawMoney(amount);
	}

}
