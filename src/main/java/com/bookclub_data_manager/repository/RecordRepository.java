package com.bookclub_data_manager.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<Record, Integer> {

    @Query(value = "SELECT * FROM records", nativeQuery = true)
    List<Record> getAllRecords(@Param("record_id")int meeting_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO records (meeting_id, user_id, is_passed, rating, sale) VALUES (:book_id, :place, :datetime, :price)", nativeQuery = true)
    void addRecord(@Param("meeting_id")int meeting_id, @Param("user_id")int user_id, @Param("is_passed")boolean is_passed, @Param("rating")float rating, @Param("sale")int sale);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM records WHERE meeting_id = :meeting_id", nativeQuery = true)
    void deleteRecordByMeeting(@Param("meeting_id")int meeting_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM records WHERE user_id = :user_id", nativeQuery = true)
    void deleteRecordByUser(@Param("user_id")int user_id);
}
