package com.smart.building.smart_building_room_meeting;

import com.smart.building.smart_building_room_meeting.model.Department;
import com.smart.building.smart_building_room_meeting.model.Room;
import com.smart.building.smart_building_room_meeting.repository.DepartmentRepository;
import com.smart.building.smart_building_room_meeting.repository.RoomRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class GetRoomTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    @Disabled
    public void getDepartmentByIdSuccess() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Room room = new Room(2);
        Room dbRoom = roomRepository.save(room);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<Room> response = restTemplate.exchange("http://localhost:" + port + "/room/" + String.valueOf(dbRoom.getId()), HttpMethod.GET, request, Room.class);
        assertEquals(response.getBody().getId(), dbRoom.getId());
    }

    @Test
    public void getDepartmentByIdFailed() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/room/9999", HttpMethod.GET, request, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
