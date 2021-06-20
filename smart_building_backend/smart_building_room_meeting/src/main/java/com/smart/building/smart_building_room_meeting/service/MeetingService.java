package com.smart.building.smart_building_room_meeting.service;

import com.smart.building.smart_building_room_meeting.model.Meeting;
import com.smart.building.smart_building_room_meeting.model.Room;
import com.smart.building.smart_building_room_meeting.payload.ApiResponse;
import com.smart.building.smart_building_room_meeting.payload.StartMeeting;
import com.smart.building.smart_building_room_meeting.repository.MeetingRepository;
import com.smart.building.smart_building_room_meeting.repository.RoomRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private RoomRepository roomRepository;

    public ResponseEntity<?> saveMeeting(Meeting meeting) {
        meetingRepository.save(meeting);
        return ResponseEntity.ok(new ApiResponse(true, "Meeting created successfully"));
    }

    public String assignMeeting(int meetingId, int roomId) {
        Meeting meeting = meetingRepository.findById(meetingId).get();
        Room room = roomRepository.findById(roomId).get();
        if (meeting.getRoom() == null) {
            meeting.setRoom(room);
            meetingRepository.save(meeting);
            return "Meeting assign room";
        }
        return "Cannot assign meeting";

    }

    public String releaseMeeting(int meetingId, int roomId) {
        Meeting meeting = meetingRepository.findById(meetingId).get();
        if (meeting.getRoom().getId() == roomId) {
            meeting.setRoom(null);
            meetingRepository.save(meeting);
            return "Meeting release";
        }
        return "Meeting cannot be release";
    }

    public String startMeeting(int id, StartMeeting startMeeting) {
        int duration = startMeeting.getDuration();
        Meeting meeting = meetingRepository.findById(id).get();
        Date currentTime = new Date();
        Date startTime = startMeeting.getStartAt();
        meeting.setStartAt(startTime);
        meeting.setDuration(duration);
        Date end = DateUtils.addMinutes(startTime, duration);
        meeting.setEndAt(end);
        System.out.println("Start at " + meeting.getStartAt());
        System.out.println("End at " + meeting.getEndAt());
        System.out.println("Current " + currentTime);
        meetingRepository.save(meeting);
        return "meeting start";
    }

    public String stopMeeting(int id) {
        Meeting meeting = meetingRepository.findById(id).get();
        meeting.setStartAt(null);
        meeting.setEndAt(null);
        meeting.setDuration(null);
        meetingRepository.save(meeting);
        return "meeting stop";
    }

}
