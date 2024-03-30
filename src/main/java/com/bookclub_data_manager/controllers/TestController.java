package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity add()(@)
}
