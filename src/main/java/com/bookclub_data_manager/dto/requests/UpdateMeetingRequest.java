package com.bookclub_data_manager.dto.requests;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateMeetingRequest {

    private int meeting_id;

    private String book_name;
    private List<String> author;
    private String place;
    private LocalDateTime datetime;
    private int price;

    public List<String> getAuthor() { return author; }

    public void setAuthor(List<String> author) { this.author = author; }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
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
}
