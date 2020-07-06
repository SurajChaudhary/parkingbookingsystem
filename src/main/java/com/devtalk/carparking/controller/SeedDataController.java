package com.devtalk.carparking.controller;

import com.devtalk.carparking.exception.SeedDataNotFoundException;
import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.service.SeedDataService;
import org.apache.commons.collections4.CollectionUtils;
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

    @PostMapping(value = "states")
    public ResponseEntity<String> addStates(@RequestBody List<String> states) {
        seedDataService.addStates(states);
        return new ResponseEntity<>("StateEntity added successfully", HttpStatus.CREATED);
    }

    @GetMapping(value = "states", produces = "application/json")
    public ResponseEntity<List<String>> getStates() {
        List<String> states = seedDataService.getStates();
        if (CollectionUtils.isEmpty(states))
            throw new SeedDataNotFoundException("Unable to fetch the states data");
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @PostMapping(value = "cities", consumes = "application/json")
    public ResponseEntity<String> addCities(@RequestBody List<City> cities) {
        seedDataService.addCities(cities);
        return new ResponseEntity<>("StateEntity added successfully", HttpStatus.CREATED);
    }


    @GetMapping(value = "cities/{stateName}", produces = "application/json")
    public ResponseEntity<List<String>> getCities(@PathVariable(value = "stateName") String stateName) {
        List<String> cities = seedDataService.getCitiesByStateName(stateName);
        if (CollectionUtils.isEmpty(cities))
            throw new SeedDataNotFoundException("Unable to fetch the cities for given state");
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void SeedDataNotFoundExceptionHandler(SeedDataNotFoundException seedDataNotFoundException) {

    }


}