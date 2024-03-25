package com.bookclub_data_manager.services.book;

import com.bookclub_data_manager.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public Integer getIdByName(String name){
        return genreRepository.getIdByName(name);
    }

}
