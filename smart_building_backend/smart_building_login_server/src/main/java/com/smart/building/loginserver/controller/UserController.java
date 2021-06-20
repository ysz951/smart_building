package com.smart.building.loginserver.controller;

import com.smart.building.loginserver.exception.ResourceNotFoundException;
import com.smart.building.loginserver.model.User;
import com.smart.building.loginserver.payload.UserSummary;
import com.smart.building.loginserver.repository.UserRepository;
import com.smart.building.loginserver.security.CurrentUser;
import com.smart.building.loginserver.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public List<UserSummary> getCurrentUser() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> new UserSummary(user.getId(), user.getUsername(), user.getName(), user.getRole()))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getRole());
        return userSummary;
    }

    @GetMapping("/users/name/{username}")
    public UserSummary getUserByName(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserSummary userSummary = new UserSummary(user.getId(), user.getUsername(), user.getName(), user.getRole());

        return userSummary;
    }

    @GetMapping("/users/{id}")
    public UserSummary getUserById(@PathVariable(value = "id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user_id", id));

        UserSummary userSummary = new UserSummary(user.getId(), user.getUsername(), user.getName(), user.getRole());

        return userSummary;
    }
}
