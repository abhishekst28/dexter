package com.db.hackathon.dexter;

import com.db.hackathon.dexter.controller.DexterController;
import com.db.hackathon.dexter.model.Questions;
import com.db.hackathon.dexter.model.TransactionData;
import com.db.hackathon.dexter.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest
class ApplicationTests {

	@Autowired
	DexterController dexterController;

	@BeforeEach
	public void setup(){
		dexterController.setup();
	}


	//@Test
	void contextLoads() throws URISyntaxException {
		TransactionData data = new TransactionData();
		data.setAmount("100");
		data.setReceiver("0987654321");
		data.setSender("1234567890");

		assert dexterController.makePayment(data);
	}

	@Test
	void findUser() {
		int userId = 1;
		User user = dexterController.getUser(userId);
		assert null!=user;
		assert "Mahesh".equals(user.getFirstName());
	}

	//@Test
	void getQuestionsForUser() {
		int userId = 1;
		List<Questions> questionsList = dexterController.getQuestion(userId);
		assert null!=questionsList;
		assert 3==questionsList.size();
	}
}
