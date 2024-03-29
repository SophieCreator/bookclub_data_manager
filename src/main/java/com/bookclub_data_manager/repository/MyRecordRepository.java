package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.MyRecord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyRecordRepository extends CrudRepository<MyRecord, Integer> {

    @Query(value = "SELECT * FROM records", nativeQuery = true)
    List<MyRecord> getAllMyRecords();

    @Query(value = "SELECT * FROM records WHERE user_id = :user_id", nativeQuery = true)
    List<MyRecord> getMyRecordsByUser(@Param("user_id")int user_id);

    @Query(value = "SELECT * FROM records WHERE meeting_id = :meeting_id", nativeQuery = true)
    List<MyRecord> getMyRecordsByMeeting(@Param("meeting_id")int meeting_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO records (record_id, meeting_id, user_id) VALUES (:record_id, :meeting_id, :user_id)", nativeQuery = true)
    void addMyRecord(@Param("record_id")int record_id, @Param("meeting_id")int meeting_id, @Param("user_id")int user_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE records SET is_passed = :is_passed", nativeQuery = true)
    void updateMyRecordPassed(@Param("is_passed")boolean is_passed);

    @Modifying
    @Transactional
    @Query(value = "UPDATE records SET rating = :rating WHERE user_id = :user_id", nativeQuery = true)
    void updateMyRecordRating(@Param("rating")Float rating, @Param("user_id")int user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM records WHERE meeting_id = :meeting_id", nativeQuery = true)
    void deleteMyRecordByMeeting(@Param("meeting_id")int meeting_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM records WHERE user_id = :user_id", nativeQuery = true)
    void deleteMyRecordByUser(@Param("user_id")int user_id);
}
