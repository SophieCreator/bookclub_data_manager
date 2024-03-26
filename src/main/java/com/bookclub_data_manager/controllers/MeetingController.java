package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.requests.AddMeetingRequest;
import com.bookclub_data_manager.dto.requests.UpdateMeetingRequest;
import com.bookclub_data_manager.services.book.BookCardService;
import com.bookclub_data_manager.services.book.BookService;
import com.bookclub_data_manager.services.meeting.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("app/meeting")
public class MeetingController {

    @Autowired
    MeetingService meetingService;

    @Autowired
    BookService bookService;

    @Autowired
    BookCardService bookCardService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody AddMeetingRequest addMeetingRequest){

        String book_name = addMeetingRequest.getBook_name();
        List<String> author = addMeetingRequest.getAuthor();
        String place = addMeetingRequest.getPlace();
        Date datetime = addMeetingRequest.getDatetime();
        int price = addMeetingRequest.getPrice();

        Integer book_id = bookService.getIdByName(book_name);

        if (book_id == null) {
            bookCardService.addOnlyBookNameAndAuthor(book_name, author);
        }

        String request = meetingService.add(book_id, place, datetime, price);

        if (Objects.equals(request, "OK")){
            return new ResponseEntity("Информация о встрече добавлена!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam int meeting_id){

        String request = meetingService.deleteMeetingById(meeting_id);

        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Информация о встрече успешно удалена", HttpStatus.OK);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody UpdateMeetingRequest updateMeetingRequest) {

        int meeting_id = updateMeetingRequest.getMeeting_id();
        String book_name = updateMeetingRequest.getBook_name();
        List<String> author = updateMeetingRequest.getAuthor();
        String place = updateMeetingRequest.getPlace();
        Date datetime = updateMeetingRequest.getDatetime();
        int price = updateMeetingRequest.getPrice();

        Integer book_id = bookService.getIdByName(book_name);

        String request = meetingService.updateMeeting(place, datetime, price, meeting_id);
        String request1 = bookCardService.updateOnlyBookNameAndAuthor(book_id, book_name, author);

        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Информация о встрече успешно обновлена", HttpStatus.OK);
        } else if (Objects.equals(request1, "OK")){
            return new ResponseEntity("Информация о встрече успешно обновлена", HttpStatus.OK);
        } else {
            return new ResponseEntity(request1, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get")
    public ResponseEntity update(@RequestParam int meeting_id) {

        String request = meetingService.getMeeting(meeting_id);

        if (!Objects.equals(request, "OK")) {
            return new ResponseEntity("Данных о встрече нет", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

}
