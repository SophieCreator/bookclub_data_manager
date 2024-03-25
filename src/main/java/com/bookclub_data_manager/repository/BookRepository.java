package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.Book;
import com.bookclub_data_manager.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM books", nativeQuery = true)
    List<Book> getAllBooks();

    @Query(value = "SELECT name FROM books WHERE name = :name", nativeQuery = true)
    List<String> doesBookExists(@Param("name")String name);

    @Query(value = "SELECT book_id FROM books WHERE name = :name", nativeQuery = true)
    int getIdByName(@Param("name")String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO book_and_author (book_id, author_id) VALUES (:book_id, :author_id)", nativeQuery = true)
    int setBookAndAuthor(@Param("book_id")int book_id, @Param("author_id")int author_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO book_and_genre (book_id, genre_id) VALUES (:book_id, :genre_id)", nativeQuery = true)
    int setBookAndGenre(@Param("book_id")int book_id, @Param("genre_id")int genre_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO books (name, pages, litres_rating, live_lib_rating) VALUES (:name, :pages, :litres_rating, :live_lib_rating)", nativeQuery = true)
    int add(@Param("name")String name, @Param("pages")int pages, @Param("litres_rating")float litres_rating, @Param("live_lib_rating")float live_lib_rating);


}
