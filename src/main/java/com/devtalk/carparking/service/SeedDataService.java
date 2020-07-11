package com.devtalk.carparking.service;

import com.devtalk.carparking.model.request.CityRequest;
import com.devtalk.carparking.model.request.StateRequest;
import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.model.seeddata.State;

import java.util.List;

public interface SeedDataService {
    List<String> addNewStatesToSystem(List<StateRequest> states);

    List<State> getStates();

    List<String> addNewCitiesToSystem(List<CityRequest> cities);

    City getCityDetailsByName(String cityName);

    List<City> getCities();
}
