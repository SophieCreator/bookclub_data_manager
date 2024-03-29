package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.models.MyRecord;
import com.bookclub_data_manager.services.meeting.MeetingService;
import com.bookclub_data_manager.services.meeting.MyRecordService;
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
public class MyRecordController {

    @Autowired
    MyRecordService recordService;

    @Autowired
    MeetingService meetingService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestParam("record_id") int record_id,
                              @RequestParam("meeting_id") int meeting_id,
                              @RequestParam("user_id") int user_id){

        String result = recordService.add(record_id, meeting_id, user_id);

        if (Objects.equals(result, "OK")){
            return new ResponseEntity("Вы записаны на встречу!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam("meeting_id") int meeting_id,
                                 @RequestParam("user_id") int user_id) {

        String requestMeeting = recordService.deleteMyRecordByMeeting(meeting_id);
        String requestUser = recordService.deleteMyRecordByUser(user_id);

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

        String requestRating = recordService.updateMyRecordRating(rating, user_id);

        if (Objects.equals(requestRating, "OK")) {
            return new ResponseEntity("Рейтинг встречи обновлен", HttpStatus.OK);
        } else {
            return new ResponseEntity(requestRating, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateDatetime")
    public ResponseEntity updateRating(@RequestParam("meeting_id") int meeting_id){

        Date datetime = meetingService.getDatetime(meeting_id);
        String requestPassed = recordService.updateMyRecordPassed(datetime);

        if (Objects.equals(requestPassed, "OK")) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(requestPassed, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getForMeeting")
    public ResponseEntity getForMeeting(@RequestParam("meeting_id") int meeting_id){

        List<MyRecord> requestMeeting = recordService.getMyRecordsByMeeting(meeting_id);

        if (requestMeeting == null){
            return new ResponseEntity("Данных о записях на встречи нет", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(requestMeeting, HttpStatus.OK);
        }
    }

    @PostMapping("/getForUser")
    public ResponseEntity getForUser(@RequestParam("user_id") int user_id){

        List<MyRecord> requestUser = recordService.getMyRecordsByUser(user_id);

        if (requestUser == null){
            return new ResponseEntity("Данных о записях на встречу нет", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(requestUser, HttpStatus.OK);
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity getAll() {

        List<MyRecord> result = recordService.getAllMyRecords();

        if (result == null){
            return new ResponseEntity("Список встреч пуст", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }
}
