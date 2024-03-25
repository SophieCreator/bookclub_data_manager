package com.bookclub_data_manager.dto.requests;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class AddBookRequest {

    private String name;
    private List<String> authors;
    private List<String> genres;
    private int pages;
    private float litres_rating;
    private float live_lib_rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getLitres_rating() {
        return litres_rating;
    }

    public void setLitres_rating(float litres_rating) {
        this.litres_rating = litres_rating;
    }

    public float getLive_lib_rating() {
        return live_lib_rating;
    }

    public void setLive_lib_rating(float live_lib_rating) {
        this.live_lib_rating = live_lib_rating;
    }
}
