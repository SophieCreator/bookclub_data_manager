package com.bookclub_data_manager.dto.responses;

import com.bookclub_data_manager.models.User;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class MeetingResponse {

    private int meeting_id;
    private int book_id;
    private String place;
    private LocalDateTime datetime;
    private int price;
    private String is_passed;
    private String book;
    private List<User> users;

    public MeetingResponse(int meeting_id, int book_id, String place, LocalDateTime datetime, int price, String book, List<User> users) {
        this.meeting_id = meeting_id;
        this.book_id = book_id;
        this.place = place;
        this.datetime = datetime;
        this.price = price;
        String is_passed = "1";
        if (datetime.isBefore(LocalDateTime.now())){
            is_passed = "0";
        }
        this.is_passed = is_passed;
        this.book = book;
        this.users = users;
    }

    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIs_passed() {
        return is_passed;
    }

    public void setIs_passed(String is_passed) {
        this.is_passed = is_passed;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
