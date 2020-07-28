package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dataaccess.entity.CityEntity;
import com.devtalk.carparking.dataaccess.entity.StateEntity;
import com.devtalk.carparking.dataaccess.entity.UserRoleEntity;
import com.devtalk.carparking.dataaccess.repository.CityRepository;
import com.devtalk.carparking.dataaccess.repository.StateRepository;
import com.devtalk.carparking.dataaccess.repository.UserRoleRepository;
import com.devtalk.carparking.exception.SeedDataNotFoundException;
import com.devtalk.carparking.model.request.CityRequest;
import com.devtalk.carparking.model.request.StateRequest;
import com.devtalk.carparking.model.request.UserRoleRequest;
import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.model.seeddata.State;
import com.devtalk.carparking.service.SeedDataService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class SeedDataServiceImpl implements SeedDataService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public SeedDataServiceImpl(CityRepository cityRepository,
                               StateRepository stateRepository,
                               UserRoleRepository userRoleRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<String> addNewStatesToSystem(List<StateRequest> states) {
        if (CollectionUtils.isEmpty(states)) {
            return Collections.emptyList();
        }
        List<StateEntity> stateEntities = states
                .stream()
                .filter(Objects::nonNull)
                .map(StateEntity::getEntityFromStateRequest)
                .collect(Collectors.toList());
        try {
            List<StateEntity> savedEntities = stateRepository.saveAll(stateEntities);
            return savedEntities.stream().map(StateEntity::getStateName).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new RuntimeException("Failed to save sates.", exception);
        }
    }

    @Override
    public List<State> getStates() {
        return stateRepository.findAll()
                .stream()
                .map(StateEntity::getStateFromStateEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> addNewCitiesToSystem(List<CityRequest> cities) {
        if (CollectionUtils.isEmpty(cities)) {
            return Collections.emptyList();
        }
        List<CityEntity> cityEntities = cities
                .stream()
                .map(CityEntity::getEntityFromCityRequest)
                .collect(Collectors.toList());

        try {
            List<CityEntity> savedCities = cityRepository.saveAll(cityEntities);
            return savedCities.stream().map(CityEntity::getCityName).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new RuntimeException("Failed to save cities.", exception);
        }
    }

    @Override
    public City getCityDetailsByName(String cityName) {
        CityEntity cityByName = cityRepository.findByCityName(cityName);
        if (cityByName == null) {
            throw new SeedDataNotFoundException("City '" + cityName + "' does not exist in system.");
        }
        return CityEntity.getCityFromCityEntity(cityByName);
    }

    @Override
    public List<City> getCities() {
        return cityRepository.findAll()
                .stream()
                .map(CityEntity::getCityFromCityEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> addNewRolesToSystem(List<UserRoleRequest> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return Collections.emptyList();
        }
        Set<UserRoleEntity> userRoleEntities = roles.stream().map(UserRoleEntity::getEntityFromModel).collect(Collectors.toSet());
        List<UserRoleEntity> savedEntities = userRoleRepository.saveAll(userRoleEntities);
        return savedEntities.stream().map(UserRoleEntity::getRoleName).collect(Collectors.toList());
    }
}
