package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.models.Test;
import com.bookclub_data_manager.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("app/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    QuestionService questionService;


    @PostMapping("/getAllForUser")
    public ResponseEntity getAllTestsForUser(@RequestParam("user_id") int user_id){
        testService.updateComplexity();
        List<Test> tests = testService.getAllForUser(user_id);
        if(tests.isEmpty()){
            return new ResponseEntity("Cписок пуст", HttpStatus.OK);
        }
        return new ResponseEntity(tests, HttpStatus.OK);
    }

}
