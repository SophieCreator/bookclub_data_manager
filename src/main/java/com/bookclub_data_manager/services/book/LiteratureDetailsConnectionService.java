package com.bookclub_data_manager.services.book;

import com.bookclub_data_manager.repository.AuthorRepository;
import com.bookclub_data_manager.repository.BookRepository;
import com.bookclub_data_manager.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiteratureDetailsConnectionService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    GenreRepository genreRepository;

    public String set(Integer bookId, List<String> authors, List<String> genres){
        for (String author : authors) {
            bookRepository.setBookAndAuthor(bookId, authorRepository.getIdByName(author));
        }
        if(!genres.isEmpty()){
            for (String genre : genres) {
                bookRepository.setBookAndGenre(bookId, genreRepository.getIdByName(genre));
            }
        }
        return "1";
    }

    public String unset(Integer bookId){
        bookRepository.unsetBookAndAuthor(bookId);
        bookRepository.unsetBookAndAuthor(bookId);
        return "1";
    }

}
