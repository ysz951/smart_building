package com.smart.building.smart_building_room_meeting.controller;

import com.smart.building.smart_building_room_meeting.model.Meeting;
import com.smart.building.smart_building_room_meeting.model.Room;
import com.smart.building.smart_building_room_meeting.payload.StartMeeting;
import com.smart.building.smart_building_room_meeting.repository.MeetingRepository;
import com.smart.building.smart_building_room_meeting.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private MeetingService meetingService;

    @GetMapping("/all")
    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Meeting getMeetingById(@PathVariable(value = "id") int id) {
        return meetingRepository.findById(id).get();
    }

    @PostMapping("/save")
    public ResponseEntity<?> createRoom() {
        Meeting meeting = new Meeting();
        return meetingService.saveMeeting(meeting);
    }

    @PutMapping("/{meetingId}/assign")
    public String assignMeeting(@PathVariable(value = "meetingId") int meetingId, @RequestParam("room") int roomId) {
        return meetingService.assignMeeting(meetingId, roomId);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(value = "id") int id) {
        meetingRepository.deleteById(id);
        return "delete";
    }

    @PutMapping("/{meetingId}/release")
    public String releaseMeeting(@PathVariable(value = "meetingId") int meetingId, @RequestParam("room") int roomId) {
        return meetingService.releaseMeeting(meetingId, roomId);
    }

    @GetMapping("/idle")
    public List<Meeting> getIdleMeeting() {
        return meetingRepository.findAllIdleMeeting();
    }

    @PutMapping("/{id}/start")
    public String startMeeting(@PathVariable(value = "id") int id, @RequestBody @Valid StartMeeting startMeeting) {
        return meetingService.startMeeting(id, startMeeting);
    }

    @PutMapping("/{id}/stop")
    public String startMeeting(@PathVariable(value = "id") int id) {
        return meetingService.stopMeeting(id);
    }
}
