package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.requests.AddMeetingRequest;
import com.bookclub_data_manager.dto.requests.MeetingRequest;
import com.bookclub_data_manager.services.book.BookService;
import com.bookclub_data_manager.services.meeting.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("app/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody AddMeetingRequest addMeetingRequest){

        String book_name = addMeetingRequest.getBook_name();
        String author = addMeetingRequest.getAuthor();
        String place = addMeetingRequest.getPlace();
        Date datetime = addMeetingRequest.getDatetime();
        int price = addMeetingRequest.getPrice();

        Integer book_id = bookService.getIdByName(book_name);

        if (book_id == null) {
            bookService.add(book_name, author, null, null, null);
        }

        String addMeeting = meetingService.add(book_id, place, datetime, price);

        if (!Objects.equals(addMeeting, "1")){
            return new ResponseEntity(addMeeting, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Информация о встрече добавлена!", HttpStatus.CREATED);
    }

}
