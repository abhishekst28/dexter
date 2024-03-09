package com.db.hackathon.dexter.service;

import com.db.hackathon.dexter.model.Questions;
import com.db.hackathon.dexter.model.TransactionData;
import com.db.hackathon.dexter.model.User;

import java.net.URISyntaxException;
import java.util.List;

public interface DexterService {
    Boolean makePayment(TransactionData transactionData) throws URISyntaxException;

    User getUser(Integer userId);

    List<Questions> getQuestion(Integer userId);

    boolean setup();
}
