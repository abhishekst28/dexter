package com.db.hackathon.dexter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Questions {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String question;
    private String answer;
    private Integer userId;

    public Questions() {
    }

    public Questions(Integer id, String question, String answer, Integer userId) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
