package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Meeting;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RecordRepository {

    @Query(value = "SELECT * FROM records", nativeQuery = true)
    List<Record> getAllRecords();

    @Query(value = "SELECT * FROM records WHERE user_id = :user_id", nativeQuery = true)
    List<Record> getRecordsByUser(@Param("user_id")int user_id);

    @Query(value = "SELECT * FROM records WHERE meeting_id = :meeting_id", nativeQuery = true)
    List<Record> getRecordsByMeeting(@Param("meeting_id")int meeting_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO records (meeting_id, user_id) VALUES (:meeting_id, :user_id)", nativeQuery = true)
    void addRecord(@Param("meeting_id")int meeting_id, @Param("user_id")int user_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE records SET is_passed = :is_passed", nativeQuery = true)
    void updateRecordPassed(@Param("is_passed")boolean is_passed);

    @Modifying
    @Transactional
    @Query(value = "UPDATE records SET rating = :rating WHERE user_id = :user_id", nativeQuery = true)
    void updateRecordRating(@Param("rating")Float rating, @Param("user_id")int user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM records WHERE meeting_id = :meeting_id", nativeQuery = true)
    void deleteRecordByMeeting(@Param("meeting_id")int meeting_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM records WHERE user_id = :user_id", nativeQuery = true)
    void deleteRecordByUser(@Param("user_id")int user_id);

}
