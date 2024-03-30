package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.requests.AddBookRequest;
import com.bookclub_data_manager.dto.requests.AddFavouriteBookRequest;
import com.bookclub_data_manager.dto.requests.UpdateBookRequest;
import com.bookclub_data_manager.dto.responses.BookCardResponse;
import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.Book;
import com.bookclub_data_manager.models.Genre;
import com.bookclub_data_manager.services.book.AuthorService;
import com.bookclub_data_manager.services.book.BookCardService;
import com.bookclub_data_manager.services.book.BookService;
import com.bookclub_data_manager.services.book.GenreService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("app/bookCard")
public class BookCardController {

    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;

    @Autowired
    GenreService genreService;

    @Autowired
    BookCardService bookCardService;


    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequest addBookRequest){
        String name = addBookRequest.getName();
        List<String> authors = addBookRequest.getAuthors();
        List<String> genres = addBookRequest.getGenres();
        Integer pages = addBookRequest.getPages();
        Float litres_rating = addBookRequest.getLitres_rating();
        Float live_lib_rating = addBookRequest.getLive_lib_rating();

        String request = bookCardService.addBookAuthorGenre(name, pages, litres_rating, live_lib_rating, authors, genres);
        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Книга успешно добавлена", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity deleteBook(@RequestParam int bookId){

        String request = bookCardService.deleteBookById(bookId);
        if(Objects.equals(request, "OK")){
            return new ResponseEntity("Книга успешно удалена", HttpStatus.OK);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateBook(@RequestBody UpdateBookRequest updateBookRequest){
        int book_id = updateBookRequest.getBook_id();
        String name = updateBookRequest.getName();
        List<String> authors = updateBookRequest.getAuthors();
        List<String> genres = updateBookRequest.getGenres();
        Integer pages = updateBookRequest.getPages();
        Float litres_rating = updateBookRequest.getLitres_rating();
        Float live_lib_rating = updateBookRequest.getLive_lib_rating();

        String request = bookCardService.updateBookAuthorGenre(book_id, name, pages, litres_rating, live_lib_rating, authors, genres);

        if(Objects.equals(request, "OK")){
            return new ResponseEntity("Книга успешно обновлена", HttpStatus.OK);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get")
    public ResponseEntity getBook(@RequestParam int book_id){

        Book book = bookService.getBookById(book_id);

        if (book == null){
            return new ResponseEntity("Нет данных о книге", HttpStatus.BAD_REQUEST);
        }

        List<Author> authors = authorService.getAuthors(book_id);
        if (authors.isEmpty()){
            return new ResponseEntity("Нет данных по авторам", HttpStatus.BAD_REQUEST);
        }

        List<Genre> genres = genreService.getGenres(book_id);
        BookCardResponse bookCardResponse = new BookCardResponse(book, authors, genres);

        if(book == null || authors.isEmpty()){
            return new ResponseEntity("Нет данных по книге", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(bookCardResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity getAllBooks(){

        List<BookCardResponse> bookCardResponses = new ArrayList<>();
        List<Integer> allBookIds = bookService.getAllBookIds();

        for (int book_id : allBookIds){
            Book book = bookService.getBookById(book_id);

            if (book == null){
                return new ResponseEntity("Нет данных по книге", HttpStatus.BAD_REQUEST);
            }
            List<Author> authors = authorService.getAuthors(book_id);

            if (authors.isEmpty()){
                return new ResponseEntity("Нет данных по авторам", HttpStatus.BAD_REQUEST);
            }
            List<Genre> genres = genreService.getGenres(book_id);
            BookCardResponse bookCardResponse = new BookCardResponse(book, authors, genres);
            bookCardResponses.add(bookCardResponse);
        }

        if(bookCardResponses == null){
            return new ResponseEntity("Нет данных по книге", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(bookCardResponses, HttpStatus.OK);
        }
    }

    @PostMapping("/addFavouriteBook")
    public ResponseEntity addFavouriteBook(@RequestBody AddFavouriteBookRequest addFavouriteBookRequest){
        String request = bookCardService.addOnlyBookNameAndAuthor(addFavouriteBookRequest.getBookName(), addFavouriteBookRequest.getAuthors());
        bookCardService.setBookAndUserDependencies(bookService.getIdByName(addFavouriteBookRequest.getBookName()), addFavouriteBookRequest.getUser_id());

        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Любимая книга успешно добавлена", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteFavouriteBook")
    public ResponseEntity deleteFavouriteBook(@RequestParam Integer book_id,
                                              @RequestParam Integer user_id){
        String request = bookCardService.unsetBookAndUserDependencies(book_id, user_id);
        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Любимый автор успешно удалена", HttpStatus.OK);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addFavouriteAuthor")
    public ResponseEntity addFavouriteAuthor(@RequestParam String authorName,
                                             @RequestParam int user_id){
        String request = authorService.add(authorName);
        bookCardService.setAuthorAndUserDependencies(authorService.getIdByName(authorName), user_id);

        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Любимый автор успешно добавлена", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteFavouriteAuthor")
    public ResponseEntity deleteFavouriteAuthor(@RequestParam int author_id,
                                                @RequestParam int user_id){

        String request = bookCardService.unsetAuthorAndUserDependencies(author_id, user_id);
        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Любимый автор успешно удален", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/addFavouriteGenre")
    public ResponseEntity addFavouriteGenre(@RequestParam String genreName,
                                             @RequestParam int user_id){

        String request = genreService.add(genreName);
        bookCardService.setGenreAndUserDependencies(genreService.getIdByName(genreName), user_id);

        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Любимый жанр успешно добавлен", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteFavouriteGenre")
    public ResponseEntity deleteFavouriteGenre(@RequestParam int genre_id,
                                                @RequestParam int user_id){

        String request = bookCardService.unsetGenreAndUserDependencies(genre_id, user_id);

        if (Objects.equals(request, "OK")) {
            return new ResponseEntity("Любимый жанр успешно удален", HttpStatus.CREATED);
        } else {
            return new ResponseEntity(request, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getFavouriteBooks")
    public ResponseEntity getFavouriteBooks(@RequestParam int user_id){
        List<Book> books = bookService.getFavouriteBooks(user_id);
        return new ResponseEntity(books, HttpStatus.OK);
    }

    @PostMapping("/getFavouriteAuthors")
    public ResponseEntity getFavouriteAuthors(@RequestParam int user_id){
        List<Author> authors = authorService.getFavouriteAuthors(user_id);
        return new ResponseEntity(authors, HttpStatus.OK);
    }

    @PostMapping("/getFavouriteGenres")
    public ResponseEntity getFavouriteGenres(@RequestParam int user_id){
        List<Genre> genres = genreService.getFavouriteGenres(user_id);
        return new ResponseEntity(genres, HttpStatus.OK);
    }


}
