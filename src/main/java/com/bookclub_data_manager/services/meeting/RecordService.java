package com.bookclub_data_manager.services.meeting;

import com.bookclub_data_manager.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    public String add(Integer meeting_id, Integer user_id) {
        recordRepository.addRecord(meeting_id, user_id);
        return "OK";
    }

    public String updateRecordPassed(Date datetime) {

        LocalDateTime currentDateTime = java.time.LocalDateTime.now();

        if (currentDateTime.isEqual(datetime.toLocalDate().atTime(LocalTime.MIDNIGHT))) ;

        recordRepository.updateRecordPassed(true);

        return "OK";
    }

    public String updateRecordRating(float rating, int user_id) {
        recordRepository.updateRecordRating(rating, user_id);
        return "OK";
    }

    public String deleteRecordByMeeting(int meeting_id) {
        recordRepository.deleteRecordByMeeting(meeting_id);
        return "OK";
    }

    public String deleteRecordByUser(int user_id) {
        recordRepository.deleteRecordByUser(user_id);
        return "OK";
    }

    public List<Record> getAllRecords() {
        return recordRepository.getAllRecords();
    }

    public List<Record> getRecordsByUser(int user_id) {
        return recordRepository.getRecordsByUser(user_id);
    }

    public List<Record> getRecordsByMeeting(int meeting_id) {
        return recordRepository.getRecordsByMeeting(meeting_id);
    }
}
