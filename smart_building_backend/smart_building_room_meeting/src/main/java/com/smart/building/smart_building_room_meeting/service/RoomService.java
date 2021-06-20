package com.smart.building.smart_building_room_meeting.service;

import com.smart.building.smart_building_room_meeting.model.Department;
import com.smart.building.smart_building_room_meeting.model.Meeting;
import com.smart.building.smart_building_room_meeting.model.Room;
import com.smart.building.smart_building_room_meeting.payload.ApiResponse;
import com.smart.building.smart_building_room_meeting.payload.RoomMeetingsAssign;
import com.smart.building.smart_building_room_meeting.repository.DepartmentRepository;
import com.smart.building.smart_building_room_meeting.repository.MeetingRepository;
import com.smart.building.smart_building_room_meeting.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public ResponseEntity<?> createRoom(Room room) {
        roomRepository.save(room);
        return ResponseEntity.ok(new ApiResponse(true, "Room created successfully"));
    }

    public String assignDepart(int roomId, int departmentId) {
        Department department = departmentRepository.findById(departmentId).get();
        Room room = roomRepository.findById(roomId).get();
        if (room.getDepart() == null) {
            room.setDepart(department);
            roomRepository.save(room);
            return "Room assign";
        }
        return "cannot assign room";
    }


    public String deleteRoom(int id) {
        Room room = roomRepository.findById(id).get();
        room.setDepart(null);
        for (Meeting room_meeting : room.getMeeting()) {
            room_meeting.setRoom(null);
            meetingRepository.save(room_meeting);
        }
        roomRepository.delete(room);
        return "Room deleted";
    }

    public String releaseRoom(int roomId, int departmentId) {
//        Department department = departmentRepository.findById(departmentId).get();
        Room room = roomRepository.findById(roomId).get();
        if (room.getDepart().getId() == departmentId) {
            room.setDepart(null);
            roomRepository.save(room);
            return "Room release";
        }
        return "Room cannot be release";
    }

    public String assignMeeting(int id, RoomMeetingsAssign roomMeetingsAssign) {
        Room room = roomRepository.findById(id).get();
        List<Integer> meetList = roomMeetingsAssign.getMeetingList();
        for (int meetId : meetList) {
            Meeting meet = meetingRepository.findById(meetId).get();
            if (meet.getRoom() == null) {
                meet.setRoom(room);
                meetingRepository.save(meet);
            }
        }
        return "Change Room meeting";
    }

}
