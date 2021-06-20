package com.smart.building.smart_building_room_meeting;

import com.smart.building.smart_building_room_meeting.model.Department;
import com.smart.building.smart_building_room_meeting.repository.DepartmentRepository;
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
public class GetDepartmentTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @Disabled
    public void getDepartmentByIdSuccess() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Department department = new Department("id_1");
        Department dbDepart = departmentRepository.save(department);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<Department> response = restTemplate.exchange("http://localhost:" + port + "/department/" + String.valueOf(dbDepart.getId()), HttpMethod.GET, request, Department.class);
        assertEquals(response.getBody().getId(), dbDepart.getId());
        assertEquals(response.getBody().getName(), dbDepart.getName());
    }

    @Test
    public void getDepartmentByIdFailed() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/department/9999", HttpMethod.GET, request, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
