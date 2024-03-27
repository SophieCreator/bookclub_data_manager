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

    @Query(value = "SELECT password FROM users WHERE user_id = :user_id", nativeQuery = true)
    String getPassword(@Param("user_id")int user_id);

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

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET name = :name, login = :login, email = :email, password = :password WHERE user_id = :user_id", nativeQuery = true)
    void update(@Param("name")String name, @Param("login")String login, @Param("email")String email, @Param("password")String password, @Param("user_id")int user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE user_id = :user_id", nativeQuery = true)
    void delete(@Param("user_id")int user_id);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "SELECT * FROM users WHERE user_id = :user_id", nativeQuery = true)
    User getUserById(@Param("user_id")int user_id);

    @Query(value = "SELECT email FROM users WHERE user_id = :user_id AND email = :email", nativeQuery = true)
    List<String> emailIsMine(@Param("user_id")int user_id, @Param("email")String email);

    @Query(value = "SELECT login FROM users WHERE user_id = :user_id AND login = :login", nativeQuery = true)
    List<String> loginIsMy(@Param("user_id")int user_id, @Param("login")String login);

}

