package com.bookclub_data_manager.services.test;

import com.bookclub_data_manager.models.MyQuestion;
import com.bookclub_data_manager.repository.MyQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyQuestionService {

    @Autowired
    MyQuestionRepository myQuestionRepository;

    @Autowired
    TestService testService;

    public String add(int test_id, String question_name, String var1, String var2, String var3, String var4) {
        myQuestionRepository.addQuestion(question_name, var1, var2, var3, var4);
        Integer question_id = myQuestionRepository.getQuestionIdByName(question_name);
        testService.setTestAndQuestionDependencies(test_id, question_id);
        return "OK";
    }

    public String update(String question_name, String var1, String var2, String var3, String var4, int question_id) {
        myQuestionRepository.updateQuestion(question_name, var1, var2, var3, var4, question_id);
        return "OK";
    }

    public String delete(int question_id) {
        myQuestionRepository.deleteQuestion(question_id);
        myQuestionRepository.unsetTestAndQuestion(question_id);
        return "OK";
    }

    public MyQuestion getQuestion(int question_id) {
        return myQuestionRepository.getQuestionById(question_id);
    }

    public List<MyQuestion> getAllQuestions() {
        return myQuestionRepository.getAllQuestions();
    }

    public List<MyQuestion> getQuestionsForTest(int test_id) {
        return myQuestionRepository.getQuestionByTestId(test_id);
    }

}
