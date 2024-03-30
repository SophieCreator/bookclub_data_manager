package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Meeting;
import com.bookclub_data_manager.models.Test;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<Test, Integer> {

    @Query(value = "SELECT * FROM tests WHERE test_id = :test_id", nativeQuery = true)
    Test getTestById(@Param("test_id") int test_id);

    @Query(value = "SELECT * FROM tests", nativeQuery = true)
    List<Test> getAllTests();

    @Query(value = "SELECT * FROM tests WHERE user_id = :user_id", nativeQuery = true)
    List<Test> getAllTestsByUserId(@Param("user_id") int user_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tests (test_name, complexity) VALUES (:test_name, :complexity)", nativeQuery = true)
    void addTest(@Param("test_name")String test_name, @Param("complexity")String complexity);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tests SET test_name = :test_name WHERE test_id = :test_id", nativeQuery = true)
    void updateTest(@Param("test_name")String test_name, @Param("test_id")int test_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tests WHERE test_id = :test_id", nativeQuery = true)
    void deleteTest(@Param("test_id")int test_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO test_and_question (test_id, question_id) VALUES (:test_id, :question_id)", nativeQuery = true)
    void setTestAndQuestion(@Param("test_id")int test_id, @Param("question_id")int question_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM test_and_question WHERE test_id = :test_id", nativeQuery = true)
    void unsetTestAndQuestion(@Param("test_id")int test_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tests SET complexity = :complexity WHERE test_id = :test_id", nativeQuery = true)
    void updateComplexity(@Param("complexity")String complexity, @Param("test_id")int test_id);

    @Query(value = "INSERT INTO user_and_test (user_id, test_id, estimation) VALUES (:user_id, :test_id, estimation)", nativeQuery = true)
    int addRatingForUser(@Param("user_id")int user_id, @Param("test_id")int test_id, @Param("estimation")double estimation);

    @Query(value = "SELECT SUM(user_id) FROM user_and_test WHERE test_id = :test_id", nativeQuery = true)
    int getAmountPeopleSolved(@Param("test_id")int test_id);

    @Query(value = "(SELECT SUM(estimation) FROM user_and_test WHERE test_id = :test_id) / (SELECT SUM(user_id) FROM user_and_test WHERE test_id = :test_id)", nativeQuery = true)
    double averageScore(@Param("test_id")int test_id);

}
