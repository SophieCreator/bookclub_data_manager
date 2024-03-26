package com.bookclub_data_manager.dto.requests;

import java.util.List;

public class UpdateBookRequest {

    private int book_id;

    private String name;
    private Integer pages;
    private Float litres_rating;
    private Float live_lib_rating;
    private List<String> authors;
    private List<String> genres;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Float getLitres_rating() {
        return litres_rating;
    }

    public void setLitres_rating(Float litres_rating) {
        this.litres_rating = litres_rating;
    }

    public Float getLive_lib_rating() {
        return live_lib_rating;
    }

    public void setLive_lib_rating(Float live_lib_rating) {
        this.live_lib_rating = live_lib_rating;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
