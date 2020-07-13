package com.devtalk.carparking.controller;

import com.devtalk.carparking.exception.FacilityNotFoundException;
import com.devtalk.carparking.model.Facility;
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
@RequestMapping("/api/v1/")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping("facilities")
    @ApiOperation(value = "Listing all the parking facilities present in system",
            notes = "In order to view parking facility list, user should be registered with the system",
            response = FacilityResponse.class
    )
    @PreAuthorize("hasAuthority('customer:read')")
    public ResponseEntity<FacilityResponse> getFacilities() {
        List<Facility> facilities = facilityService.getFacilities();
        if (CollectionUtils.isEmpty(facilities)) {
            throw new FacilityNotFoundException();
        }
        FacilityResponse response = new FacilityResponse();
        response.setFacilities(facilities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void FacilityNotFoundExceptionHandler(FacilityNotFoundException facilityNotFoundException) {
    }
}
