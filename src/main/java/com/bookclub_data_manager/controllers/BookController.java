package com.bookclub_data_manager.controllers;

import com.bookclub_data_manager.dto.requests.AddBookRequest;
import com.bookclub_data_manager.services.book.AuthorService;
import com.bookclub_data_manager.services.book.BookService;
import com.bookclub_data_manager.services.book.LiteratureDetailsConnectionService;
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
    LiteratureDetailsConnectionService literatureDetailsConnectionService;


    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequest addBookRequest){
        String name = addBookRequest.getName();;
        List<String> authors = addBookRequest.getAuthors();
        List<String> genres = addBookRequest.getGenres();
        int pages = addBookRequest.getPages();
        float litres_rating = addBookRequest.getLitres_rating();
        float live_lib_rating = addBookRequest.getLive_lib_rating();

        String book_is_pushed = bookService.add(name, pages, litres_rating, live_lib_rating);

        if(!Objects.equals(book_is_pushed, "1")){
            return new ResponseEntity(book_is_pushed, HttpStatus.BAD_REQUEST);
        }

        String author_is_pushed = "";

        for (int i = 0; i < authors.size(); i++){
            author_is_pushed = authorService.add(authors.get(i));
        }

        if(!Objects.equals(author_is_pushed, "1")){
            return new ResponseEntity(author_is_pushed, HttpStatus.BAD_REQUEST);
        }
        int bookId = bookService.getIdByName(name);

        String connectionSet = literatureDetailsConnectionService.set(bookId, authors, genres);

        return new ResponseEntity("Книга успешно добавлена", HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteBook(@RequestParam int bookId){

        String book_is_pushed = bookService.deleteById(bookId);

        if(!Objects.equals(book_is_pushed, "1")){
            return new ResponseEntity(book_is_pushed, HttpStatus.BAD_REQUEST);
        }

        String author_is_pushed = "";

        String connectionUnSet = literatureDetailsConnectionService.unset(bookId);

        if(!Objects.equals(connectionUnSet, "1")){
            return new ResponseEntity("Книга не удалена", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Книга успешно удалена", HttpStatus.OK);

    }

}
