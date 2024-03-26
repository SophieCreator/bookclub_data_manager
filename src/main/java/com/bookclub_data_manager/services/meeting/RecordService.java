package com.bookclub_data_manager.services.meeting;

import com.bookclub_data_manager.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    public String add(Integer meeting_id, Integer user_id, boolean is_passed, float rating, int sale) {
        recordRepository.addRecord(meeting_id, user_id, is_passed, rating, sale);
        return "OK";
    }

    public String deleteRecordByMeeting(int meeting_id) {
        recordRepository.deleteRecordByMeeting(meeting_id);
        return "OK";
    }

    public String deleteRecordByUser(int user_id) {
        recordRepository.deleteRecordByMeeting(user_id);
        return "OK";
    }
}
