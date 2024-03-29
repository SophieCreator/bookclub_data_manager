package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.services.meeting.MeetingService;
import com.bookclub_data_manager.services.meeting.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("app/record")
public class RecordController {

    @Autowired
    RecordService recordService;

    @Autowired
    MeetingService meetingService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestParam("meeting_id") int meeting_id,
                              @RequestParam("user_id") int user_id){

        String result = recordService.add(meeting_id, user_id);

        if (Objects.equals(result, "OK")){
            return new ResponseEntity("Вы записаны на встречу!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam("meeting_id") int meeting_id,
                                 @RequestParam("user_id") int user_id) {

        String requestMeeting = recordService.deleteRecordByMeeting(meeting_id);
        String requestUser = recordService.deleteRecordByUser(user_id);

        if (Objects.equals(requestMeeting, "OK")) {
            return new ResponseEntity("Запись на встречу успешно удалена", HttpStatus.OK);
        } else if (Objects.equals(requestUser, "OK")){
            return new ResponseEntity("Запись на встречу успешно удалена", HttpStatus.OK);
        } else {
            return new ResponseEntity(requestUser, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateRating")
    public ResponseEntity updateRating(@RequestParam("rating") Float rating,
                                 @RequestParam("user_id") int user_id){

        String requestRating = recordService.updateRecordRating(rating, user_id);

        if (Objects.equals(requestRating, "OK")) {
            return new ResponseEntity("Рейтинг встречи обновлен", HttpStatus.OK);
        } else {
            return new ResponseEntity(requestRating, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateDatetime")
    public ResponseEntity updateRating(@RequestParam("meeting_id") int meeting_id){

        Date datetime = meetingService.getDatetime(meeting_id);
        String requestPassed = recordService.updateRecordPassed(datetime);

        if (Objects.equals(requestPassed, "OK")) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(requestPassed, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getForMeeting")
    public ResponseEntity getForMeeting(@RequestParam("meeting_id") int meeting_id){

        List<Record> requestMeeting = recordService.getRecordsByMeeting(meeting_id);

        if (requestMeeting == null){
            return new ResponseEntity("Данных о записях на встречи нет", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(requestMeeting, HttpStatus.OK);
        }
    }

    @PostMapping("/getForUser")
    public ResponseEntity getForUser(@RequestParam("user_id") int user_id){

        List<Record> requestUser = recordService.getRecordsByUser(user_id);

        if (requestUser == null){
            return new ResponseEntity("Данных о записях на встречу нет", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(requestUser, HttpStatus.OK);
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity getAll() {

        List<Record> result = recordService.getAllRecords();

        if (result == null){
            return new ResponseEntity("Список встреч пуст", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }
}
