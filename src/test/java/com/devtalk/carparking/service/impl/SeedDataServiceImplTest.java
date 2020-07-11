package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dataaccess.entity.StateEntity;
import com.devtalk.carparking.dataaccess.repository.CityRepository;
import com.devtalk.carparking.dataaccess.repository.StateRepository;
import com.devtalk.carparking.model.seeddata.State;
import com.devtalk.carparking.service.SeedDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class SeedDataServiceImplTest {

    @MockBean
    private StateRepository stateRepository;

    @MockBean
    private CityRepository cityRepository;

    private SeedDataService seedDataService;

    @BeforeEach
    void setUp() {
        stateRepository = Mockito.mock(StateRepository.class);
        cityRepository = Mockito.mock(CityRepository.class);
        seedDataService = new SeedDataServiceImpl(cityRepository, stateRepository);
    }

    @Test
    void addStates() {
    }

    @Test
    void getStates() {
        //stateRepository.findAll().stream().map(StateEntity::getStateName).collect(Collectors.toList());
        StateEntity stateEntity = new StateEntity();
        stateEntity.setStateName("Delhi");
        given(stateRepository.findAll()).willReturn(Collections.singletonList(stateEntity));
        List<State> stateNames = seedDataService.getStates();
        Assertions.assertEquals(stateNames, Collections.singletonList(StateEntity.getStateFromStateEntity(stateEntity)));

    }

    @Test
    void addCities() {
    }

    @Test
    void getCitiesByStateName() {
        /*CityEntity cityEntity = new CityEntity(1, "Delhi", "New Delhi");
        given(cityRepository.findAllByStateName("Delhi")).willReturn(Collections.singletonList(cityEntity));
        List<String> cityNames = seedDataService.getCitiesByStateName("Delhi");
        Assertions.assertEquals(cityNames, Collections.singletonList(cityEntity.getName()));*/
    }
}