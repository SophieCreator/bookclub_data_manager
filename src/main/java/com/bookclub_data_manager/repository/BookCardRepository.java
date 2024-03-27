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
public interface BookCardRepository extends CrudRepository<Book, Integer> {


    @Query(value = "SELECT author_id FROM book_and_author WHERE book_id = :book_id", nativeQuery = true)
    List<Integer> getAuthorsIdByBook(@Param("book_id") int book_id);

    @Query(value = "SELECT genre_id FROM book_and_genre WHERE book_id = :book_id", nativeQuery = true)
    List<Integer> getGenresIdByBook(@Param("book_id") int book_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO book_and_author (book_id, author_id) VALUES (:book_id, :author_id)", nativeQuery = true)
    void setBookAndAuthor(@Param("book_id")int book_id, @Param("author_id")int author_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO book_and_genre (book_id, genre_id) VALUES (:book_id, :genre_id)", nativeQuery = true)
    void setBookAndGenre(@Param("book_id")int book_id, @Param("genre_id")int genre_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM book_and_author WHERE book_id = :book_id", nativeQuery = true)
    void unsetBookAndAuthor(@Param("book_id")int book_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM book_and_genre WHERE book_id = :book_id", nativeQuery = true)
    void unsetBookAndGenre(@Param("book_id")int book_id);


}
