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
    @Query(value = "INSERT INTO books (name, pages, litres_rating, live_lib_rating) VALUES (:name, :pages, :litres_rating, :live_lib_rating)", nativeQuery = true)
    void add(@Param("name")String name, @Param("pages")Integer pages, @Param("litres_rating")Float litres_rating, @Param("live_lib_rating")Float live_lib_rating);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM books WHERE book_id = :book_id", nativeQuery = true)
    void deleteById(@Param("book_id")int book_id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE books SET name = :name, pages = :pages, litres_rating = :litres_rating, live_lib_rating = :live_lib_rating  WHERE book_id = :book_id", nativeQuery = true)
    void updateById(@Param("name")String name, @Param("pages")Integer pages, @Param("litres_rating")Float litres_rating, @Param("live_lib_rating")Float live_lib_rating, @Param("book_id")int book_id);

}
