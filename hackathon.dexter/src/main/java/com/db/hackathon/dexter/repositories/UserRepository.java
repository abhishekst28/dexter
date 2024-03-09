package com.db.hackathon.dexter.repositories;

import com.db.hackathon.dexter.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {

    private Map<Integer,User> userMap = new HashMap<>();

    public void save(User user){
        userMap.put(user.getId(),user);
    }

    public User findByPhoneNum(String phoneNum){
        for (Integer i: userMap.keySet()) {
            User user = userMap.get(i);
            if(user.getPhoneNum().equals(phoneNum))
                return user;
        }
        return null;
    }

    public User findById(Integer userId){
        return userMap.get(userId);
    }
}