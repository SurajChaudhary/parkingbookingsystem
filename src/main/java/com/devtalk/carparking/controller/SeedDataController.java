package com.devtalk.carparking.controller;

import com.devtalk.carparking.model.seeddata.State;
import com.devtalk.carparking.service.SeedDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/")
public class SeedDataController {
    private final SeedDataService seedDataService;

    public SeedDataController(SeedDataService seedDataService) {
        this.seedDataService = seedDataService;
    }

    @PostMapping(value = "states", consumes = "application/json")
    public ResponseEntity<String> addStates(@RequestBody List<String> states) {
        seedDataService.addStates(states);
        return new ResponseEntity<>("StateEntity added successfully", HttpStatus.CREATED);
    }

    @GetMapping(value = "states", produces = "application/json")
    public ResponseEntity<List<String>> getStates() {
        List<String> states = seedDataService.getStates();
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @PostMapping(value = "cities", consumes = "application/json")
    public ResponseEntity<String> addCities(@RequestBody List<State> states) {
        seedDataService.addCities(states);
        return new ResponseEntity<>("StateEntity added successfully", HttpStatus.CREATED);
    }


}
