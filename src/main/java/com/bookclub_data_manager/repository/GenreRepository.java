package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.Book;
import com.bookclub_data_manager.models.Genre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {

    @Query(value = "SELECT * FROM genres", nativeQuery = true)
    List<Genre> getAllGenres();
    @Query(value = "SELECT * FROM genres WHERE genre_id = :genre_id", nativeQuery = true)
    Genre getGenre(@Param("genre_id")int genre_id);

    @Query(value = "SELECT * FROM genres WHERE genre_id IN (SELECT genre_id FROM book_and_genre WHERE book_id = :book_id)", nativeQuery = true)
    List<Genre> getGenres(@Param("book_id")int book_id);

    @Query(value = "SELECT * FROM genres WHERE genre_id IN (SELECT genre_id FROM favourite_genres WHERE user_id = :user_id)", nativeQuery = true)
    List<Genre> getFavouriteGenres(@Param("user_id")int user_id);

    @Query(value = "SELECT genre_id FROM genres WHERE name = :name", nativeQuery = true)
    int getIdByName(@Param("name")String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO genres (name) VALUES (:name)", nativeQuery = true)
    void add(@Param("name")String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM genres WHERE genre_id = :genre_id", nativeQuery = true)
    void delete(@Param("genre_id")int genre_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE genres SET name = :name WHERE genre_id = :genre_id", nativeQuery = true)
    void update(@Param("name")String name, @Param("genre_id")int genre_id);

}
