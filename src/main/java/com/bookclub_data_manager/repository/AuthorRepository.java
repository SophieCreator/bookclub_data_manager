package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    @Query(value = "SELECT * FROM authors", nativeQuery = true)
    List<Author> getAllAuthors();

    @Query(value = "SELECT * FROM authors WHERE author_id IN (SELECT author_id FROM book_and_author WHERE book_id = :book_id)", nativeQuery = true)
    List<Author> getAuthors(@Param("book_id")int book_id);

    @Query(value = "SELECT * FROM authors WHERE author_id IN (SELECT author_id FROM favourite_authors WHERE user_id = :user_id)", nativeQuery = true)
    List<Author> getFavouriteAuthors(@Param("user_id")int user_id);

    @Query(value = "SELECT * FROM authors WHERE author_id = :author_id", nativeQuery = true)
    Author getAuthor(@Param("author_id")int author_id);

    @Query(value = "SELECT name FROM authors WHERE name = :name", nativeQuery = true)
    List<String> doesAuthorExists(@Param("name")String name);

    @Query(value = "SELECT author_id FROM authors WHERE name = :name", nativeQuery = true)
    Integer getIdByName(@Param("name")String name);

    @Query(value = "SELECT name FROM authors WHERE author_id = :author_id", nativeQuery = true)
    String getNameById(@Param("author_id")int author_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO authors (name) VALUES (:name)", nativeQuery = true)
    void add(@Param("name")String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM authors WHERE author_id = :author_id", nativeQuery = true)
    void deleteById(@Param("author_id")int author_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE authors SET name = :name WHERE author_id = :author_id", nativeQuery = true)
    void update(@Param("name")String name, @Param("author_id")int author_id);

}
