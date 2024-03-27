package com.bookclub_data_manager.services.book;

import com.bookclub_data_manager.models.Genre;
import com.bookclub_data_manager.repository.GenreRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public String add(String genre){
        if (genre == null){
            return "Пустая строка";
        }
        if (getIdByName(genre) != null){
            return "OK";
        }
        genreRepository.add(genre);
        return "OK";
    }

    public String addList(List<String> genres){
        if (genres.isEmpty()){
            return "Пустой список";
        }
        for (String genre : genres){
            add(genre);
        }
        return "OK";
    }

    public String delete(Integer id){
        if (id == null){
            return "Нет идентификатора";
        }
        genreRepository.delete(id);
        return "OK";
    }

    public String update(String name, Integer id){
        if (id == null){
            return "Нет идентификатора";
        }
        genreRepository.update(name, id);
        return "OK";
    }
    public String updateList(List<String> genres, List<Integer> ids){
        if (genres.isEmpty()){
            return "Пустой список";
        }
        for (int i = 0; i < genres.size(); i++){
            String genre = genres.get(i);
            Integer id = ids.get(i);
            update(genre, id);
        }
        return "OK";
    }

    public List<Genre> getGenres(int book_id){
        return genreRepository.getGenres(book_id);
    }


    public Integer getIdByName(String name){
        return genreRepository.getIdByName(name);
    }



}
