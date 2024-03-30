package com.bookclub_data_manager.services.test;

import com.bookclub_data_manager.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public String add(String question_name, String var1, String var2, String var3, String var4) {
        questionRepository.addQuestion(question_name, var1, var2, var3, var4);
        return "OK";
    }

    public String update(String question_name, int question_id) {
        questionRepository.
    }
}
