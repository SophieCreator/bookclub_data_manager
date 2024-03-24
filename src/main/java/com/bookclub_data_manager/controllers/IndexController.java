package com.bookclub_data_manager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String getHome(){
        return "Home page";
    }

    @GetMapping("/app/admin")
    public String getAdmin(){
        return "Admin page";
    }


    @GetMapping("/test")
    public String getTest(){
        return "Hello";
    }
}
