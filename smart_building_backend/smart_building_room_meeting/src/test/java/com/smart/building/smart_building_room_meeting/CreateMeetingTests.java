package com.smart.building.smart_building_room_meeting;

import com.smart.building.smart_building_room_meeting.model.Meeting;
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
public class CreateMeetingTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void saveRoomSuccess() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Meeting meeting = new Meeting();
        HttpEntity<Meeting> request = new HttpEntity<>(meeting, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/meeting/save", HttpMethod.POST, request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
