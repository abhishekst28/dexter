package com.db.hackathon.dexter.repositories;

import com.db.hackathon.dexter.model.Questions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestionsRepository {

    private Map<Integer, List<Questions>> questionMap = new HashMap<>();

    public void save(Questions questions){
        List<Questions> questionList = questionMap.get(questions.getUserId());

        if(null == questionList)
            questionList = new ArrayList<>();

        questionList.add(questions);

        questionMap.put(questions.getUserId(),questionList);
    }

    public List<Questions> findByUserIdIs(Integer userId){
        return questionMap.get(userId);
    }

}