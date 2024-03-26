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

    List<String> doesBookExists(String name){
        return bookRepository.doesBookExists(name);
    }

    List<String> doesBookExists(int id){
        return bookRepository.doesBookExists(id);
    }
    public Integer getIdByName(String name){
        return bookRepository.getIdByName(name);
    }

    public String add(String name, Integer pages, Float litres_rating, Float live_lib_rating){
        if(!doesBookExists(name).isEmpty()){
            return "Книга уже есть в списке";
        }
        bookRepository.add(name, pages, litres_rating, live_lib_rating);
        return "OK";
    }

    public String update(String name, Integer pages, Float litres_rating, Float live_lib_rating, int bookId){
        if (name == null){
            return "Поле названия должно быть заполнено";
        }
        bookRepository.updateById(name, pages, litres_rating, live_lib_rating, bookId);
        return "OK";
    }

    public String deleteById(int bookId){
        if(doesBookExists(bookId).isEmpty()){
            return "Книги c таким идентификатором нет в списке";
        }
        bookRepository.deleteById(bookId);
        return "OK";
    }

}
