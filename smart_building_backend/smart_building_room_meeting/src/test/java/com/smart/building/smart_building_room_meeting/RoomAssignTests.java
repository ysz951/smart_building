package com.smart.building.smart_building_room_meeting;

import com.smart.building.smart_building_room_meeting.model.Department;
import com.smart.building.smart_building_room_meeting.model.Room;
import com.smart.building.smart_building_room_meeting.repository.DepartmentRepository;
import com.smart.building.smart_building_room_meeting.repository.RoomRepository;
import com.smart.building.smart_building_room_meeting.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class RoomAssignTests {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoomService roomService;

    @Test
    public void RoomAssignDepart() {
        Room room1 = new Room(1);
        Department depart1 = new Department("depart1");
        roomRepository.save(room1);
        departmentRepository.save(depart1);
        roomService.assignDepart(1, 1);
        Room assignRoom1 = roomRepository.findById(1).get();
        assertEquals(assignRoom1.getDepart().getId(), 1);
    }
}
