package com.devtalk.carparking.controller;

import com.devtalk.carparking.configuration.auth.ApplicationUserService;
import com.devtalk.carparking.exception.FacilityNotFoundException;
import com.devtalk.carparking.model.Facility;
import com.devtalk.carparking.model.request.ApplicationUserRequest;
import com.devtalk.carparking.model.request.UserRoleRequest;
import com.devtalk.carparking.model.response.ApplicationUserResponse;
import com.devtalk.carparking.model.response.FacilityResponse;
import com.devtalk.carparking.service.FacilityService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    public ApplicationUserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }


    @ApiOperation(value = "This API can be used to create multiple application users.",
            notes = "In order to create application users, requester must have ADMIN role in the system.",
            response = FacilityResponse.class
    )
    @PostMapping(value = "users", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<List<ApplicationUserResponse>> addNewUsersToSystem(@RequestBody List<ApplicationUserRequest> applicationUsers) {
        List<ApplicationUserResponse> savedUsers = applicationUserService.addNewUsersToSystem(applicationUsers);
        return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void FacilityNotFoundExceptionHandler(FacilityNotFoundException facilityNotFoundException) {
    }
}
