package com.bookclub_data_manager.dto.responses;

import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.Book;
import com.bookclub_data_manager.models.Genre;

import java.util.List;

public class BookCardResponse {
    private Book book;
    private List<Genre> genres;

    private List<Author> authors;
    public BookCardResponse(Book book, List<Author> authors, List<Genre> genres){
        this.book = book;
        this.authors = authors;
        this.genres = genres;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
