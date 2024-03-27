package com.bookclub_data_manager.services.book;

import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.Book;
import com.bookclub_data_manager.repository.AuthorRepository;
import com.bookclub_data_manager.repository.BookRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public List<String> doesAuthorExists(String name){
        return authorRepository.doesAuthorExists(name);
    }

    public Integer getIdByName(String name){
        return authorRepository.getIdByName(name);
    }
    public String update(String author, int author_id){
        authorRepository.update(author, author_id);
        return "OK";
    }

    public Author getAuthorById(int id){
        return authorRepository.getAuthor(id);
    }

    public List<Author> getAuthors(int book_id){
        return authorRepository.getAuthors(book_id);
    }

    public String updateList(List<String> authors, List<Integer> ids){
        if (authors.isEmpty()){
            return "No author";
        }
        if (ids.isEmpty()){
            return "No author";
        }
        for (int i = 0; i < authors.size(); i++){
            String author = authors.get(i);
            Integer id = ids.get(i);
            update(author, id);
        }
        return "OK";
    }

    public String add(String name) {
        if (name == null){
            return "cannot add empty name";
        }
        if (!doesAuthorExists(name).isEmpty())
        {
            return "OK";
        }
        authorRepository.add(name);
        return "OK";
    }

    public String addList(List<String> names){
        if (names.isEmpty()){
            return "cannot add empty list";
        }
        for (String name : names){
             add(name);
        }
        return "OK";
    }

    public List<Author> getFavouriteAuthors(int user_id){
        return authorRepository.getFavouriteAuthors(user_id);
    }
}
