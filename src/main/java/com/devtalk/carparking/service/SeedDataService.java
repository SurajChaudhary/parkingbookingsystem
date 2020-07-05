package com.devtalk.carparking.service;

import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.model.seeddata.State;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeedDataService {
     void addStates(List<String> states);

     List<String> getStates();

    void addCities(List<City> cities);

    List<String> getCitiesByStateName(String stateName);
}
