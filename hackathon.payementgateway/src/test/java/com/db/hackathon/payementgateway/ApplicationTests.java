package com.db.hackathon.payementgateway;

import com.db.hackathon.payementgateway.controller.PaymentGatewayController;
import com.db.hackathon.payementgateway.model.Account;
import com.db.hackathon.payementgateway.model.TransactionData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Autowired
	PaymentGatewayController paymentGatewayController;

	@Test
	void contextLoads() {
		assert paymentGatewayController.setup();

		Account account = paymentGatewayController.getAccount(1);
		assert account.getFirstName().equals("Mahesh");

		TransactionData data = new TransactionData();
		data.setAmount("100");
		data.setReceiver("0987654321");
		data.setSender("1234567890");
		data.setToken("1234");
		assert paymentGatewayController.doPayment(data);
	}

}
