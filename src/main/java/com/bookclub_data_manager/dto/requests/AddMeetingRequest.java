package com.bookclub_data_manager.dto.requests;

import java.util.Date;

public class AddMeetingRequest {

    private String book_name;
    private String author;
    private String place;
    private Date datetime;
    private int price;

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getBook_name() { return book_name; }

    public void setBook_name(String book_name) { this.book_name = book_name; }

    public String getPlace() { return place; }

    public void setPlace(String place) { this.place = place; }

    public Date getDatetime() { return datetime; }

    public void setDatetime(Date datetime) { this.datetime = datetime; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

}
