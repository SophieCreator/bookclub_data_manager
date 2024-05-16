package com.bookclub_data_manager.dto.responses;

import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.Book;
import com.bookclub_data_manager.models.Genre;
import com.bookclub_data_manager.models.User;

import java.util.List;

public class UserInfoResponse {

    private int user_id;
    private String name;
    private String login;
    private String email;
    private String is_admin;
    private String visited_meetings;

    List<Book> favouriteBooks;
    List<Author> favouriteAuthors;
    List<Genre> favouriteGenres;
    public UserInfoResponse(User user, List<Book> favouriteBooks, List<Author> favouriteAuthors, List<Genre> favouriteGenres, String visited){
        this.user_id = user.getUser_id();
        this.name = user.getName();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.is_admin = user.getIs_admin();
        this.visited_meetings = visited;
        this.favouriteBooks = favouriteBooks;
        this.favouriteGenres = favouriteGenres;
        this.favouriteAuthors = favouriteAuthors;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getVisited_meetings() {
        return visited_meetings;
    }

    public void setVisited_meetings(String visited_meetings) {
        this.visited_meetings = visited_meetings;
    }

    public List<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public void setFavouriteBooks(List<Book> favouriteBooks) {
        this.favouriteBooks = favouriteBooks;
    }

    public List<Author> getFavouriteAuthors() {
        return favouriteAuthors;
    }

    public void setFavouriteAuthors(List<Author> favouriteAuthors) {
        this.favouriteAuthors = favouriteAuthors;
    }

    public List<Genre> getFavouriteGenres() {
        return favouriteGenres;
    }

    public void setFavouriteGenres(List<Genre> favouriteGenres) {
        this.favouriteGenres = favouriteGenres;
    }
}
