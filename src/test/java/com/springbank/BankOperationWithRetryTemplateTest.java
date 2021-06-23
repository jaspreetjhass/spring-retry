package com.springbank;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BankOperationWithRetryTemplateTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void createBankAccount() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(URI.create("/api/retry/template/operations")))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void withdrawMoney() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(URI.create("/api/retry/template/operations/withdrawCash")).param("amount",
				"1000.90")).andDo(MockMvcResultHandlers.print());
	}

	@Test
	void cashWithdrawal() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/retry/template/operations/{accountId}", "ABC1234"))
				.andDo(MockMvcResultHandlers.print());
	}

}
