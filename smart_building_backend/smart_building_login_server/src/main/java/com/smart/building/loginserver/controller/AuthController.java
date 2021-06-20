package com.smart.building.loginserver.controller;


import com.smart.building.loginserver.model.AuthProvider;
import com.smart.building.loginserver.model.Role;
import com.smart.building.loginserver.model.User;
import com.smart.building.loginserver.payload.ApiResponse;
import com.smart.building.loginserver.payload.JwtAuthenticationResponse;
import com.smart.building.loginserver.payload.LoginRequest;
import com.smart.building.loginserver.payload.SignUpRequest;
import com.smart.building.loginserver.repository.UserRepository;
import com.smart.building.loginserver.security.CurrentUser;
import com.smart.building.loginserver.security.TokenProvider;
import com.smart.building.loginserver.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        loginRequest.setUsernameOrEmail(loginRequest.getUsernameOrEmail().toLowerCase());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail().toLowerCase(),
                        loginRequest.getPassword()
                )
        );
        System.out.println("sign in");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, userRepository.findByEmailAndProviderId(loginRequest.getUsernameOrEmail().toLowerCase(), null).get().getRole()));
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmailAndProviderId(signUpRequest.getEmail(), null)) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        if (signUpRequest.getRole() == null) signUpRequest.setRole(Role.ROLE_USER);
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail().toLowerCase(), signUpRequest.getRole());
        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        System.out.println(user.getRole());
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));

    }

    @PostMapping("/jwt/refresh")
    public ResponseEntity<?> refresh(@CurrentUser UserPrincipal currentUser) {
        String jwt = tokenProvider.refreshToken(currentUser);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, currentUser.getRole()));

    }
}
