package com.db.hackathon.dexter.dao.impl;

import com.db.hackathon.dexter.dao.DexterDaoService;
import com.db.hackathon.dexter.model.Questions;
import com.db.hackathon.dexter.model.User;
import com.db.hackathon.dexter.repositories.QuestionsRepository;
import com.db.hackathon.dexter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DexterDaoServiceImpl implements DexterDaoService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Override
    public List<Questions> getQuestions(Integer userId) {
        return questionsRepository.findByUserIdIs(userId);
    }

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public String getSecureToken(String sender) {
        return userRepository.findByPhoneNum(sender).getSecureToken();
    }

    @Override
    public boolean setup() {
        userRepository.save(new User(1,"Mahesh","More","1234567890","1234"));
        userRepository.save(new User(2,"Megha","Bijlani","0987654321","1234"));
        userRepository.save(new User(3,"Anmol","Narang","0147258369","1234"));

        questionsRepository.save(new Questions(1,"What is pet name of your best friend","coco",1));
        questionsRepository.save(new Questions(2,"What is your favourite travel destination","london",1));
        questionsRepository.save(new Questions(3,"What is your first school teacher name","john",1));

        questionsRepository.save(new Questions(4,"What is pet name of your best friend","randy",2));
        questionsRepository.save(new Questions(5,"What is your favourite travel destination","manali",2));
        questionsRepository.save(new Questions(6,"What is your first school teacher name","john",2));

        questionsRepository.save(new Questions(7,"What is pet name of your best friend","tommy",3));
        questionsRepository.save(new Questions(8,"What is your favourite travel destination","kerala",3));
        questionsRepository.save(new Questions(9,"What is your first school teacher name","john",3));
        return true;
    }
}
