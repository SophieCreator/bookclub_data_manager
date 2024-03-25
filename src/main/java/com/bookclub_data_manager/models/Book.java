package com.bookclub_data_manager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    private int book_id;

    private String name;
    private int pages;
    private float litres_rating;
    private float live_lib_rating;

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
