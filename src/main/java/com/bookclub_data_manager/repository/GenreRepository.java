package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {

    @Query(value = "SELECT genre_id FROM genres WHERE name = :name", nativeQuery = true)
    int getIdByName(@Param("name")String name);

}
