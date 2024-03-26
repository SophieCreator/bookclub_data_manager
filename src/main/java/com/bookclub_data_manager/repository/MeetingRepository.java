package com.bookclub_data_manager.repository;

import com.bookclub_data_manager.models.Meeting;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Integer> {

    @Query(value = "SELECT * FROM meetings", nativeQuery = true)
    List<Meeting> getAllMeetings(@Param("meeting_id")int meeting_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO meetings (book_id, place, datetime, price) VALUES (:book_id, :place, :datetime, :price)", nativeQuery = true)
    void addMeeting(@Param("book_id")int book_id, @Param("place")String place, @Param("datetime")Date datetime, @Param("price")int price);

    @Modifying
    @Transactional
    @Query(value = "UPDATE meetings SET book_id = :book_id, place = :place, datetime = :datetime, price = :price  WHERE meeting_id = :meeting_id", nativeQuery = true)
    void updateMeetingBook(@Param("book_id")int book_id, @Param("place")String place, @Param("datetime")Date datetime, @Param("price")int price);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM meetings WHERE meeting_id = :meeting_id", nativeQuery = true)
    void deleteMeetingById(@Param("meeting_id")int meeting_id);
}
