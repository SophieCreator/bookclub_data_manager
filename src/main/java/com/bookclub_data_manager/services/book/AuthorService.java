package com.bookclub_data_manager.services.book;

import com.bookclub_data_manager.repository.AuthorRepository;
import com.bookclub_data_manager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    List<String> doesAuthorExists(String name){
        return authorRepository.doesAuthorExists(name);
    }

    Integer getIdByName(String name){
        return authorRepository.getIdByName(name);
    }

    public String add(String name) {
        if (doesAuthorExists(name).isEmpty())
        {
            return String.valueOf((authorRepository.add(name)));
        }
        return "1";
    }
}
