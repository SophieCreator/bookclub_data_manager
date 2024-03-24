package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT email FROM users WHERE email = :email", nativeQuery = true)
    List<String> doesEmailExist(@Param("email")String email);

    @Query(value = "SELECT login FROM users WHERE login = :login", nativeQuery = true)
    List<String> doesLoginExist(@Param("login")String email);

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User getUserByEmail(@Param("email")String email);

    @Query(value = "SELECT * FROM users WHERE login = :login", nativeQuery = true)
    User getUserByLogin(@Param("login")String login);

    @Query(value = "SELECT user_id FROM users WHERE email = :email ", nativeQuery = true)
    int getUserIdByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (name, login, email, password, is_admin) VALUES (:name, :login, :email, :password, :is_admin)", nativeQuery = true)
    int registerUser(@Param("name")String name, @Param("login")String login, @Param("email")String email, @Param("password")String password, @Param("is_admin")String admin);
}

