package com.smart.building.smart_building_room_meeting;

import com.smart.building.smart_building_room_meeting.model.Room;
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
public class CreateRoomTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void saveRoomSuccess() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Room room = new Room(2);
        HttpEntity<Room> request = new HttpEntity<>(room, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/room/save", HttpMethod.POST, request, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void saveRoomFailed() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Room room = new Room(0);
        HttpEntity<Room> request = new HttpEntity<>(room, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/room/save", HttpMethod.POST, request, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
