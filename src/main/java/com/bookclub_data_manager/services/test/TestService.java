package com.bookclub_data_manager.services.test;

import com.bookclub_data_manager.models.Test;
import com.bookclub_data_manager.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public String add(String test_name, String complexity) {
        testRepository.addTest(test_name, complexity);
        updateComplexity();
        return "OK";
    }

    public String update(String test_name, int test_id) {
        testRepository.updateTest(test_name, test_id);
        return "OK";
    }

    public String deleteTest(int test_id) {
        testRepository.deleteTest(test_id);
        unsetTestAndQuestionDependencies(test_id);
        return "OK";
    }

    public String setTestAndQuestionDependencies(Integer test_id, Integer question_id) {
        if (test_id == null || question_id == null) {
            return "Нет идентификатора теста или вопроса";
        }
        testRepository.setTestAndQuestion(test_id, question_id);
        return "OK";
    }

    public String unsetTestAndQuestionDependencies(Integer test_id) {
        testRepository.unsetTestAndQuestion(test_id);
        return "OK";
    }

    public Test getTest(int test_id) {
        return testRepository.getTestById(test_id);
    }

    public List<Test> getAllTests() {
        return testRepository.getAllTests();
    }

    public List<Test> getAllForUser(int user_id) {
        return testRepository.getAllTestsByUserId(user_id);
    }

    public String addRatingForUser(int user_id, int test_id, double estimation) {
        testRepository.addRatingForUser(user_id, test_id, estimation);
        return "OK";
    }

    public void updateComplexity() {
        List<Test> tests = testRepository.getAllTests();
        for (Test test : tests) {
            String complexity;
            int amount = testRepository.getAmountPeopleSolved(test.getTest_id());
            if (amount < 10) {
                complexity = "Новый тест";
            } else {
                double score = testRepository.averageScore(test.getTest_id());
                if (score > 90) {
                    complexity = "Лёгкий уровень";
                } else if (score > 60) {
                    complexity = "Средний уровень";
                } else {
                    complexity = "Сложный уровень";
                }
                testRepository.updateComplexity(complexity, test.getTest_id());
            }
        }
    }
}
