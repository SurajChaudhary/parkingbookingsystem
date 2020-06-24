package com.devtalk.carparking.controller;

import com.devtalk.carparking.model.Facility;
import com.devtalk.carparking.model.response.FacilityResponse;
import com.devtalk.carparking.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping("/facilities")
    public ResponseEntity<FacilityResponse> getFacilities() {
        List<Facility> facilities = facilityService.getFacilities();
        FacilityResponse response = new FacilityResponse();
        response.setFacilities(facilities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
