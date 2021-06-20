package com.smart.building.loginserver;

import com.smart.building.loginserver.model.User;
import com.smart.building.loginserver.payload.LoginRequest;
import com.smart.building.loginserver.payload.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserSignupTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void userSignupSuccess() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SignUpRequest signupRequest = new SignUpRequest();
        signupRequest.setEmail("test1@u.com");
        signupRequest.setName("test1");
        signupRequest.setUsername("test1");
        signupRequest.setPassword("test1Password");
        HttpEntity<SignUpRequest> signupEntity= new HttpEntity<>(signupRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/auth/signup", HttpMethod.POST, signupEntity, String.class);
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Test
    public void userSignupUserNameTaken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SignUpRequest signupRequest = new SignUpRequest();
        signupRequest.setEmail("test2@u.com");
        signupRequest.setName("test2");
        signupRequest.setUsername("lums");
        signupRequest.setPassword("test2Password");
        HttpEntity<SignUpRequest> signupEntity= new HttpEntity<>(signupRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/auth/signup", HttpMethod.POST, signupEntity, String.class);
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void userSignupInvalidPassword() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SignUpRequest signupRequest = new SignUpRequest();
        signupRequest.setEmail("test3@u.com");
        signupRequest.setName("test3");
        signupRequest.setUsername("test3");
        signupRequest.setPassword("t");
        HttpEntity<SignUpRequest> signupEntity= new HttpEntity<>(signupRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/auth/signup", HttpMethod.POST, signupEntity, String.class);
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void userSignupEmailTaken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SignUpRequest signupRequest = new SignUpRequest();
        signupRequest.setEmail("lums@admin.com");
        signupRequest.setName("test4");
        signupRequest.setUsername("test4");
        signupRequest.setPassword("test4password");
        HttpEntity<SignUpRequest> signupEntity= new HttpEntity<>(signupRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/auth/signup", HttpMethod.POST, signupEntity, String.class);
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void nameTooLong() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SignUpRequest signupRequest = new SignUpRequest();
        signupRequest.setEmail("nametoolong@smart.com");
        signupRequest.setName("abcdefghijklmnopqrstuvwxyz");
        signupRequest.setUsername("nametoolong");
        signupRequest.setPassword("test2Password");
        HttpEntity<SignUpRequest> signupEntity= new HttpEntity<>(signupRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/auth/signup", HttpMethod.POST, signupEntity, String.class);
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void userNameTooLong() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SignUpRequest signupRequest = new SignUpRequest();
        signupRequest.setEmail("nametoolong@smart.com");
        signupRequest.setName("username");
        signupRequest.setUsername("abcdefghijklmnopqrstuvwxyz");
        signupRequest.setPassword("test2Password");
        HttpEntity<SignUpRequest> signupEntity= new HttpEntity<>(signupRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/api/auth/signup", HttpMethod.POST, signupEntity, String.class);
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
