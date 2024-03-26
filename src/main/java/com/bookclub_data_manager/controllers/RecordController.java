package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.requests.AddMeetingRequest;
import com.bookclub_data_manager.services.meeting.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/record")
public class RecordController {

    @Autowired
    RecordService recordService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody AddMeetingRequest addMeetingRequest){
        return new ResponseEntity("Вы успешно зарегистрированы!", HttpStatus.CREATED);;
    }
}
