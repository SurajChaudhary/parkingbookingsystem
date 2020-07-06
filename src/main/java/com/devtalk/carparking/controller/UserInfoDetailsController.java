package com.devtalk.carparking.controller;

import com.devtalk.carparking.model.UserInfoDetails;
import com.devtalk.carparking.service.UserInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/")
public class UserInfoDetailsController {

    private final UserInfoDetailsService userInfoDetailsService;

    @Autowired
    public UserInfoDetailsController(UserInfoDetailsService userInfoDetailsService) {
        this.userInfoDetailsService = userInfoDetailsService;
    }

    @GetMapping(value = "users", produces = "application/json")
    public ResponseEntity<List<UserInfoDetails>> getAllUsers() {
        Optional<List<UserInfoDetails>> allUsers = userInfoDetailsService.getAllUsers();
        if (allUsers.isPresent()) {
            List<UserInfoDetails> users = allUsers.get();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping(value = "users", consumes = "application/json")
    public ResponseEntity<List<String>> createUsers(@RequestBody List<UserInfoDetails> users) {
        List<String> createdUserIds = userInfoDetailsService.createUsers(users);
        return new ResponseEntity<>(createdUserIds, HttpStatus.CREATED);
    }

    @PostMapping(value = "user", consumes = "application/json")
    public ResponseEntity<String> createUsers(@RequestBody UserInfoDetails user) {
        String createdUserId = userInfoDetailsService.createUser(user);
        return new ResponseEntity<>(createdUserId, HttpStatus.CREATED);
    }
}
