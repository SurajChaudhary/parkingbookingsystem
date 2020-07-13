package com.devtalk.carparking.controller;

import com.devtalk.carparking.exception.SeedDataNotFoundException;
import com.devtalk.carparking.model.request.CityRequest;
import com.devtalk.carparking.model.request.StateRequest;
import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.model.seeddata.State;
import com.devtalk.carparking.service.SeedDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class SeedDataController {
    private final SeedDataService seedDataService;

    public SeedDataController(SeedDataService seedDataService) {
        this.seedDataService = seedDataService;
    }

    @PostMapping(value = "admin/states", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<List<String>> addNewStatesToSystem(@RequestBody List<StateRequest> states) {
        List<String> savedStates = seedDataService.addNewStatesToSystem(states);
        return new ResponseEntity<>(savedStates, HttpStatus.CREATED);
    }

    @PostMapping(value = "admin/cities", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<List<String>> addCities(@RequestBody List<CityRequest> cities) {
        List<String> savedCities = seedDataService.addNewCitiesToSystem(cities);
        return new ResponseEntity<>(savedCities, HttpStatus.CREATED);
    }

    @GetMapping(value = "admin/states", produces = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
    public ResponseEntity<List<State>> getStates() {
        List<State> states = seedDataService.getStates();
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @GetMapping(value = "admin/cities", produces = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
    public ResponseEntity<List<City>> getCities() {
        List<City> cities = seedDataService.getCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }


    @GetMapping(value = "admin/cities/{cityName}", produces = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
    public ResponseEntity<City> getCities(@PathVariable(value = "cityName") String cityName) {
        City cityDetails = seedDataService.getCityDetailsByName(cityName);
        return new ResponseEntity<>(cityDetails, HttpStatus.OK);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void SeedDataNotFoundExceptionHandler(SeedDataNotFoundException seedDataNotFoundException) {

    }


}