package com.smart.building.loginserver;

import com.smart.building.loginserver.payload.LoginRequest;
import com.smart.building.loginserver.payload.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserSigninTests {
    @LocalServerPort
    private int port;

    @Value("${test.password.admin}")
    private String testAdminPassword;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void userSigninSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        LoginRequest login = new LoginRequest();
        login.setUsernameOrEmail("lums@admin.com");
        login.setPassword(testAdminPassword);
        HttpEntity<LoginRequest> request = new HttpEntity<>(login);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/auth/signin", HttpMethod.POST, request, String.class);
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void userSigninFail() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        LoginRequest login = new LoginRequest();
        login.setUsernameOrEmail("lums@admin.com");
        login.setPassword("wrongpassword");
        HttpEntity<LoginRequest> request = new HttpEntity<>(login);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/auth/signin", HttpMethod.POST, request, String.class);
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        assertTrue(response.getStatusCode().equals(HttpStatus.UNAUTHORIZED));
    }

}
