package com.smart.building.smart_building_room_meeting;

import com.smart.building.smart_building_room_meeting.model.Department;
import com.smart.building.smart_building_room_meeting.model.Room;
import com.smart.building.smart_building_room_meeting.payload.CreateDepartment;
import com.smart.building.smart_building_room_meeting.payload.DepartmentRoomsAssign;
import com.smart.building.smart_building_room_meeting.repository.DepartmentRepository;
import com.smart.building.smart_building_room_meeting.repository.RoomRepository;
import com.smart.building.smart_building_room_meeting.service.DepartmentService;
import com.smart.building.smart_building_room_meeting.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateDepartmentTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void saveDepartmentSuccess() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Department department = new Department("test");
        HttpEntity<Department> request = new HttpEntity<>(department, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/department/save", HttpMethod.POST, request, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void saveDepartmentFailed() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CreateDepartment department = new CreateDepartment();
        department.setName("create_depart");
        HttpEntity<CreateDepartment> request = new HttpEntity<>(department, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/room/save", HttpMethod.POST, request, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
