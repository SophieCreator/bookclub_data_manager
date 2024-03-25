package com.bookclub_data_manager.services.book;

import com.bookclub_data_manager.repository.BookRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    List<String> doesBookExists(String name){
        return bookRepository.doesBookExists(name);
    }

    public String add(String name, int pages, float litres_rating, float live_lib_rating){
        if(!doesBookExists(name).isEmpty()){
            return "Книга уже есть в списке";
        }
        return String.valueOf(bookRepository.add(name, pages, litres_rating, live_lib_rating));
    }

    public Integer getIdByName(String name){
        return bookRepository.getIdByName(name);
    }


}
