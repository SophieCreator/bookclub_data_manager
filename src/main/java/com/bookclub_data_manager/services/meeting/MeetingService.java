package com.bookclub_data_manager.services.meeting;
import com.bookclub_data_manager.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class MeetingService {

    @Autowired
    MeetingRepository meetingRepository;

    public String add(Integer book_id, String place, Date datetime, int price) {
        meetingRepository.addMeeting(book_id, place, datetime, price);
        return "OK";
    }

    public String deleteMeetingById(int meeting_id) {
        meetingRepository.deleteMeetingById(meeting_id);
        return "OK";
    }

    public String updateMeeting(String place, Date datetime, int price, Integer meeting_id) {
        meetingRepository.updateMeetingBook(place, datetime, price, meeting_id);
        return "OK";
    }

    public String getMeeting(int meeting_id) {

        return "OK";
    }
}
