package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Author;
import com.bookclub_data_manager.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM authors", nativeQuery = true)
    List<Author> getAllAuthors();

    @Query(value = "SELECT name FROM authors WHERE name = :name", nativeQuery = true)
    List<String> doesAuthorExists(@Param("name")String name);

    @Query(value = "SELECT author_id FROM authors WHERE name = :name", nativeQuery = true)
    Integer getIdByName(@Param("name")String name);

    @Query(value = "SELECT name FROM authors WHERE author_id = :author_id", nativeQuery = true)
    String getNameById(@Param("author_id")int author_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO authors (name) VALUES (:name)", nativeQuery = true)
    int add(@Param("name")String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM authors WHERE author_id = :author_id", nativeQuery = true)
    int deleteById(@Param("author_id")int author_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE books SET name = :name WHERE author_id = :author_id", nativeQuery = true)
    int updateName(@Param("name")String name, @Param("author_id")int author_id);

}
