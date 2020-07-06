package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dataaccess.entity.CityEntity;
import com.devtalk.carparking.dataaccess.entity.StateEntity;
import com.devtalk.carparking.dataaccess.repository.CityRepository;
import com.devtalk.carparking.dataaccess.repository.StateRepository;
import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.service.SeedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedDataServiceImpl implements SeedDataService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    @Autowired
    public SeedDataServiceImpl(CityRepository cityRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    @Override
    public void addStates(List<String> states) {
        List<StateEntity> stateEntities = states.stream().map(stateName -> {
            StateEntity stateEntity = new StateEntity();
            stateEntity.setName(stateName);
            return stateEntity;
        }).collect(Collectors.toList());
        stateRepository.saveAll(stateEntities);
        return;
    }

    @Override
    public List<String> getStates() {
        return stateRepository.findAll().stream().map(StateEntity::getName).collect(Collectors.toList());
    }

    @Override
    public void addCities(List<City> cities) {

        List<CityEntity> cityEntities = cities.stream().map(CityEntity::getCityEntityFromCity).collect(Collectors.toList());
        cityRepository.saveAll(cityEntities);
        return;
    }

    @Override
    public List<String> getCitiesByStateName(String stateName) {
        List<CityEntity> cityEntities = cityRepository.findAllByStateName(stateName);

        List<String> cityNames = cityEntities.stream().map(CityEntity::getName).collect(Collectors.toList());

        return cityNames;
    }
}
