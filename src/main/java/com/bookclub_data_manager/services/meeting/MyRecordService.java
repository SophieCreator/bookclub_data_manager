package com.bookclub_data_manager.services.meeting;

import com.bookclub_data_manager.models.MyRecord;
import com.bookclub_data_manager.repository.MyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class MyRecordService {

    @Autowired
    MyRecordRepository recordRepository;

    public String add(Integer record_id, Integer meeting_id, Integer user_id) {
        recordRepository.addMyRecord(record_id, meeting_id, user_id);
        return "OK";
    }

    public String updateMyRecordPassed(Date datetime) {

        LocalDateTime currentDateTime = java.time.LocalDateTime.now();

        if (currentDateTime.isEqual(datetime.toLocalDate().atTime(LocalTime.MIDNIGHT))) {
            recordRepository.updateMyRecordPassed(true);
        }

        return "OK";
    }

    public String updateMyRecordRating(float rating, int user_id) {
        recordRepository.updateMyRecordRating(rating, user_id);
        return "OK";
    }

    public String deleteMyRecordByMeeting(int meeting_id) {
        recordRepository.deleteMyRecordByMeeting(meeting_id);
        return "OK";
    }

    public String deleteMyRecordByUser(int user_id) {
        recordRepository.deleteMyRecordByUser(user_id);
        return "OK";
    }

    public List<MyRecord> getAllMyRecords() {
        return recordRepository.getAllMyRecords();
    }

    public List<MyRecord> getMyRecordsByUser(int user_id) {
        return recordRepository.getMyRecordsByUser(user_id);
    }

    public List<MyRecord> getMyRecordsByMeeting(int meeting_id) {
        return recordRepository.getMyRecordsByMeeting(meeting_id);
    }
}
