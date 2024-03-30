package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Test;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository {

    @Query(value = "SELECT * FROM tests WHERE test_id = :test_id", nativeQuery = true)
    Test getTestById(@Param("test_id") int test_id);

    @Query(value = "SELECT * FROM tests", nativeQuery = true)
    List<Test> getAllTests();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tests (test_id, test_name, complexity) VALUES (:test_id, :test_name, :complexity)", nativeQuery = true)
    void addTest(@Param("test_id")int test_id, @Param("test_name")String test_name, @Param("complexity")String complexity);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tests SET test_name = :test_name WHERE test_id = :test_id", nativeQuery = true)
    void updateTest(@Param("test_name")String test_name,, @Param("test_id")int test_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tests WHERE test_id = :test_id", nativeQuery = true)
    void deleteTest(@Param("test_id")int test_id);
}
