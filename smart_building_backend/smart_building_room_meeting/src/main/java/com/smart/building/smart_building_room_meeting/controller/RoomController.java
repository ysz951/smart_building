package com.smart.building.smart_building_room_meeting.controller;

import com.smart.building.smart_building_room_meeting.exception.ResourceNotFoundException;
import com.smart.building.smart_building_room_meeting.model.Room;
import com.smart.building.smart_building_room_meeting.payload.RoomMeetingsAssign;
import com.smart.building.smart_building_room_meeting.repository.RoomRepository;
import com.smart.building.smart_building_room_meeting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @GetMapping("/all")
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable(value = "id") int id) {
        return roomRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> createRoom(@RequestBody @Valid Room room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/{roomId}/assign")
    public String assignRoom(@PathVariable("roomId") int roomId, @RequestParam("department") int departmentId) {
        return roomService.assignDepart(roomId, departmentId);
    }

    @DeleteMapping("/{id}")
    public String deleteRoomById(@PathVariable(value = "id") int id) {
        return roomService.deleteRoom(id);
    }

    @GetMapping("/idle")
    public List<Room> getIdleRoom() {
        return roomRepository.findAllIdleRoom();
    }

    @PutMapping("/{roomId}/release")
    public String releaseRoom(@PathVariable("roomId") int roomId, @RequestParam("department") int departmentId) {
        return roomService.releaseRoom(roomId, departmentId);
    }

    @PutMapping("/{id}/assign_meeting")
    public String assignRoom(@PathVariable("id") int id, @RequestBody RoomMeetingsAssign roomMeetingsAssign) {
        return roomService.assignMeeting(id, roomMeetingsAssign);
    }

}
