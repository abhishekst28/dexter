package com.db.hackathon.dexter.dao;

import com.db.hackathon.dexter.model.Questions;
import com.db.hackathon.dexter.model.User;

import java.util.List;

public interface DexterDaoService {
    List<Questions> getQuestions(Integer userId);

    User getUser(Integer userId);

    String getSecureToken(String sender);

    boolean setup();
}
