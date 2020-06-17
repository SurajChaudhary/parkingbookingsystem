package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dao.repository.CityRepository;
import com.devtalk.carparking.dao.repository.StateRepository;
import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.model.seeddata.State;
import com.devtalk.carparking.service.SeedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedDataServiceImpl implements SeedDataService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;


    @Override
    public void addStates(List<String> states) {
        return;
    }

    @Override
    public List<String> getStates() {
        return null;
    }

    @Override
    public void addCities(List<State> states) {
        states.stream().map(state -> {

            return state.getCitiNames().stream().map(s -> {
                City city= new City();
                city.setName(s);
                city.setState(state);
                return city;
            }).collect(Collectors.toList());


        }).collect(Collectors.toList());


    }
}
