package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.requests.AddMeetingRequest;
import com.bookclub_data_manager.dto.requests.UpdateMeetingRequest;
import com.bookclub_data_manager.dto.responses.MeetingResponse;
import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.Book;
import com.bookclub_data_manager.models.Meeting;
import com.bookclub_data_manager.services.book.AuthorService;
import com.bookclub_data_manager.services.book.BookCardService;
import com.bookclub_data_manager.services.book.BookService;
import com.bookclub_data_manager.services.meeting.MeetingService;
import com.bookclub_data_manager.services.meeting.MyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    AuthorService authorService;
    @Autowired
    MyRecordService recordService;

    @Autowired
    BookCardService bookCardService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody AddMeetingRequest addMeetingRequest){

        String book_name = addMeetingRequest.getBook_name();
        List<String> author = addMeetingRequest.getAuthor();
        String place = addMeetingRequest.getPlace();
        LocalDateTime datetime = addMeetingRequest.getDatetime();
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
        LocalDateTime datetime = updateMeetingRequest.getDatetime();
        int price = updateMeetingRequest.getPrice();

        Integer book_id = bookService.getIdByName(book_name);

        String requestMeeting = meetingService.updateMeeting(place, datetime, price, meeting_id);
        String requestBookAuthor = bookCardService.updateOnlyBookNameAndAuthor(book_id, book_name, author);

        if (Objects.equals(requestMeeting, "OK")) {
            return new ResponseEntity("Информация о встрече успешно обновлена", HttpStatus.OK);
        } else if (Objects.equals(requestBookAuthor, "OK")){
            return new ResponseEntity("Информация о встрече успешно обновлена", HttpStatus.OK);
        } else {
            return new ResponseEntity(requestBookAuthor, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get")
    public ResponseEntity getMeeting(@RequestParam int meeting_id) {

        Meeting meeting = meetingService.getMeeting(meeting_id);

        if (meeting == null){
            return new ResponseEntity("Нет данных о встрече", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(meeting, HttpStatus.OK);
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity getAllMeetings() {

        List<Meeting> meetings = meetingService.getAllMeetings();

        if (meetings.isEmpty()){
            return new ResponseEntity(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(meetings, HttpStatus.OK);
        }
    }

    @PostMapping("/getAllInfo")
    public ResponseEntity getAllInfo() {

        List<Meeting> meetings = meetingService.getAllMeetings();
        List<MeetingResponse> responses = new ArrayList<>();

        for (Meeting meeting : meetings){
            Book book = bookService.getBookById(meeting.getBook_id());
            List<Author> authors = authorService.getAuthors(meeting.getBook_id());
            String authorInfo = "";
            for (int i = 0; i < authors.size(); i++){
                authorInfo += authors.get(i).getName();
                if (i != authors.size() - 1){
                    authorInfo += " & ";
                }
            }

            String bookInfo = book.getName() + ", " + authorInfo;
            responses.add(new MeetingResponse(meeting.getMeeting_id(), meeting.getBook_id(), meeting.getPlace(), meeting.getDatetime(), meeting.getPrice(), bookInfo, recordService.getRecordsByMeeting(meeting.getMeeting_id())));
        }

        if (meetings.isEmpty()){
            return new ResponseEntity(null, HttpStatus.OK);
        } else {
            return new ResponseEntity(responses, HttpStatus.OK);
        }
    }
}
