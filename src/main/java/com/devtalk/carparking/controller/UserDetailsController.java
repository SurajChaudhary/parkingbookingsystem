package com.devtalk.carparking.controller;

import com.devtalk.carparking.model.UserInfoDetails;
import com.devtalk.carparking.service.UserInfoDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserDetailsController {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsController.class.getName());

    private final UserInfoDetailsService userInfoDetailsService;

    public UserDetailsController(UserInfoDetailsService userInfoDetailsService) {
        this.userInfoDetailsService = userInfoDetailsService;
    }

    @PostMapping(value = "userDetails", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'ADMIN', 'ADMIN_TRAINEE','customer:read')")
    public ResponseEntity<String> addUserDetailsToSystem(@RequestBody UserInfoDetails userDetail) {
        String createdUser = userInfoDetailsService.createUser(userDetail);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


}
