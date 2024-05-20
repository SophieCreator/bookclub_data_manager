package com.bookclub_data_manager.services.meeting;
import com.bookclub_data_manager.models.Meeting;
import com.bookclub_data_manager.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService {

    @Autowired
    MeetingRepository meetingRepository;

    public String add(Integer book_id, String place, LocalDateTime datetime, int price) {
        String datetimeS = datetime.toString().replace("T", " ");
        meetingRepository.addMeeting(book_id, place, datetimeS, price);
        return "OK";
    }

    public String deleteMeetingById(int meeting_id) {
        meetingRepository.deleteMeetingById(meeting_id);
        return "OK";
    }

    public String updateMeeting(String place, LocalDateTime datetime, int price, Integer meeting_id) {
        String datetimeS = datetime.toString().replace("T", " ");
        meetingRepository.updateMeetingBook(place, datetimeS, price, meeting_id);
        return "OK";
    }

    public LocalDateTime getDatetime(int meeting_id) {
        return meetingRepository.getDatetime(meeting_id);
    }

    public Meeting getMeeting(int meeting_id) {
        return meetingRepository.getMeetingById(meeting_id);
    }

    public List<Meeting> getAllMeetings() {
        return meetingRepository.getAllMeetings();
    }
}
