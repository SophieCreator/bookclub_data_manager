package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.requests.AddBookRequest;
import com.bookclub_data_manager.dto.requests.UpdateBookRequest;
import com.bookclub_data_manager.services.book.AuthorService;
import com.bookclub_data_manager.services.book.BookCardService;
import com.bookclub_data_manager.services.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("app/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    AuthorService authorService;

    @Autowired
    BookCardService bookCardService;


    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequest addBookRequest){
        String name = addBookRequest.getName();;
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

}
