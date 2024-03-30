package com.bookclub_data_manager.dto.responses;

import com.bookclub_data_manager.models.*;

import java.util.List;

public class TestQuestionResponse {

    private Test test;
    private List<MyQuestion> questions;

    public TestQuestionResponse(Test test, List<MyQuestion> questions) {
        this.test = test;
        this.questions = questions;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<MyQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<MyQuestion> questions) {
        this.questions = questions;
    }
}
