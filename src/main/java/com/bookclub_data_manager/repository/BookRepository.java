package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query(value = "SELECT * FROM books", nativeQuery = true)
    List<Book> getAllBooks();

    @Query(value = "SELECT name FROM books WHERE name = :name", nativeQuery = true)
    List<String> doesBookExists(@Param("name")String name);

    @Query(value = "SELECT book_id FROM books WHERE book_id = :book_id", nativeQuery = true)
    List<String> doesBookExists(@Param("book_id")int book_id);

    @Query(value = "SELECT book_id FROM books WHERE name = :name", nativeQuery = true)
    Integer getIdByName(@Param("name")String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO book_and_author (book_id, author_id) VALUES (:book_id, :author_id)", nativeQuery = true)
    Integer setBookAndAuthor(@Param("book_id")int book_id, @Param("author_id")int author_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO book_and_genre (book_id, genre_id) VALUES (:book_id, :genre_id)", nativeQuery = true)
    Integer setBookAndGenre(@Param("book_id")int book_id, @Param("genre_id")int genre_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM book_and_author WHERE book_id = :book_id", nativeQuery = true)
    Integer unsetBookAndAuthor(@Param("book_id")int book_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM book_and_genre WHERE book_id = :book_id", nativeQuery = true)
    Integer unsetBookAndGenre(@Param("book_id")int book_id);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO books (name, pages, litres_rating, live_lib_rating) VALUES (:name, :pages, :litres_rating, :live_lib_rating)", nativeQuery = true)
    Integer add(@Param("name")String name, @Param("pages")Integer pages, @Param("litres_rating")Float litres_rating, @Param("live_lib_rating")Float live_lib_rating);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM books WHERE book_id = :book_id", nativeQuery = true)
    Integer deleteById(@Param("book_id")int book_id);

}
