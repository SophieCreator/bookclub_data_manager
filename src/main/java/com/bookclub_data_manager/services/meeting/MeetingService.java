package com.bookclub_data_manager.services.meeting;
import com.bookclub_data_manager.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    public String add(String book_name, String place, Date datetime, int price) {



        return String.valueOf(meetingRepository.addMeeting(book_id, place, datetime, price);
    }
}
