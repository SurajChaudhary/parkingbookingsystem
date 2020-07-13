package com.devtalk.carparking.controller;

import com.devtalk.carparking.configuration.jwt.UserNameAndPasswordAuthenticationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Api("Authentication")
@RestController
public class AuthenticationController {
    /**
     * Implemented by Spring Security
     */
    @ApiOperation(value = "Login", notes = "Login with the given credentials.")
    @ApiResponses({@ApiResponse(code = 200, message = "", response = Authentication.class)})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    void login(@RequestBody UserNameAndPasswordAuthenticationRequest userNameAndPasswordAuthenticationRequest) {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    /**
     * Implemented by Spring Security
     *//*
    @ApiOperation(value = "Logout", notes = "Logout the current user.")
    @ApiResponses({@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    void logout() {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }*/
}