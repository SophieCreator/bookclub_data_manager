package com.bookclub_data_manager.dto.requests;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class AddFavouriteBookRequest {

    private  String bookName;

    private  List<String> authors;
    private int user_id;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
