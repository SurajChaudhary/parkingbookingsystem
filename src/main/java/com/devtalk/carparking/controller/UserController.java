package com.devtalk.carparking.controller;

import com.devtalk.carparking.model.User;
import com.devtalk.carparking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users", produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        Optional<List<User>> allUsers = userService.getAllUsers();
        if (allUsers.isPresent()) {
            List<User> users = allUsers.get();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping(value = "users", consumes = "application/json")
    public ResponseEntity<List<String>> createUsers(@RequestBody List<User> users) {
        List<String> createdUserIds = userService.createUsers(users);
        return new ResponseEntity<>(createdUserIds, HttpStatus.CREATED);
    }

    @PostMapping(value = "user", consumes = "application/json")
    public ResponseEntity<String> createUsers(@RequestBody User user) {
        String createdUserId = userService.createUser(user);
        return new ResponseEntity<>(createdUserId, HttpStatus.CREATED);
    }
}
