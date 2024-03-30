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

    public String add(Integer test_id, String test_name, String complexity) {
        testRepository.addTest(test_id, test_name, complexity);
        return "OK";
    }

    public String update(String test_name, int test_id) {
        testRepository.updateTest(test_name, test_id);
        return "OK";
    }

    public String deleteTest(int test_id) {
        testRepository.deleteTest(test_id);
        return "OK";
    }

    public Test getTest(int test_id) {
        return testRepository.getTestById(test_id);
    }

    public List<Test> getAllTests() {
        return testRepository.getAllTests();
    }

    public void updateComplexity(){
        List<Test> tests = testRepository.getAllTests();
        for(Test test : tests){
            String complexity;
            int amount = testRepository.getAmountPeopleSolved(test.getTest_id());
            if (amount < 10){
                complexity = "новый тест";
            } else {
                double score = testRepository.averageScore(test.getTest_id());
                if (score > 90){
                    complexity = "лёгкий уровень";
                } else if (score > 60){
                    complexity = "средний уровень";
                } else {
                    complexity = "сложный уровень";
                }
            }
            test.setComplexity(complexity);
        }
    }

}
