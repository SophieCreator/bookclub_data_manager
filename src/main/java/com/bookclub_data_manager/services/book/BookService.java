package com.bookclub_data_manager.services.book;

import com.bookclub_data_manager.repository.AuthorRepository;
import com.bookclub_data_manager.repository.BookRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    LiteratureDetailsConnectionService literatureDetailsConnectionService;

    List<String> doesBookExists(String name){
        return bookRepository.doesBookExists(name);
    }

    List<String> doesBookExists(int id){
        return bookRepository.doesBookExists(id);
    }

    public String add(String name, Integer pages, Float litres_rating, Float live_lib_rating){
        if(!doesBookExists(name).isEmpty()){
            return "Книга уже есть в списке";
        }
        return String.valueOf(bookRepository.add(name, pages, litres_rating, live_lib_rating));
    }

    public String addByNameAndAuthor(String name, List<String> authors){
        add(name, null, null, null);
        for (String author : authors) {
            authorRepository.add(author);
        }
        literatureDetailsConnectionService.set(bookRepository.getIdByName(name), authors, null);
        return "1";
    }

    public String deleteById(int bookId){
        if(doesBookExists(bookId).isEmpty()){
            return "Книги нет в списке";
        }
        return String.valueOf(bookRepository.deleteById(bookId));
    }


    public Integer getIdByName(String name){
        return bookRepository.getIdByName(name);
    }

}
