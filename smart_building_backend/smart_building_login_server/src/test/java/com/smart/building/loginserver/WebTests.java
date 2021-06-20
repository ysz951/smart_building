package com.smart.building.loginserver;


import com.smart.building.loginserver.payload.LoginRequest;
import com.smart.building.loginserver.payload.UserSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@SuppressWarnings("unchecked")
public class WebTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String token;

    private String testAdmin = "lums@admin.com";

    @Value("${test.password.admin}")
    private String testAdminPassword;

    @BeforeEach
    public void loginTest() {
        LoginRequest login = new LoginRequest();
        HttpEntity<LoginRequest> request = new HttpEntity<>(login);
        login.setUsernameOrEmail(testAdmin);
        login.setPassword(testAdminPassword);
        Map<String, Object> response = restTemplate.postForObject("http://localhost:" + port + "/api/auth/signin",
                request, Map.class);
        token = (String) response.get("accessToken");
        System.out.println(token);
    }


    @Test
    public void getMe() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<UserSummary> response = restTemplate.exchange("http://localhost:" + port + "/api/user/me", HttpMethod.GET, request, UserSummary.class);
        System.out.println(response.getBody());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        assertTrue(response.getBody().getName().equals("admin"));
        assertTrue(response.getBody().getUsername().equals("admin"));
    }

    @Test
    public void getUsersByName() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/users/name/admin", HttpMethod.GET, request, String.class);
        System.out.println(response.getBody());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void getUsersById() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/users/3", HttpMethod.GET, request, String.class);
        System.out.println(response.getBody());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

}
