package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dataaccess.entity.FacilityEntity;
import com.devtalk.carparking.dataaccess.repository.FacilityRepository;
import com.devtalk.carparking.exception.FacilityNotFoundException;
import com.devtalk.carparking.model.Facility;
import com.devtalk.carparking.service.FacilityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class TestFacilityServiceImpl {

    private FacilityService facilityService;

    @MockBean
    private FacilityRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(FacilityRepository.class);
        facilityService = new FacilityServiceImpl(repository);
    }

    @Test
    void test_getFacilities_givingFacilities() {
        // arrange
        FacilityEntity testEntity = new FacilityEntity();
        testEntity.setId(101);
        testEntity.setName("p1");
        testEntity.setAddress("testAddress");
        List<FacilityEntity> facilityEntities = Collections.singletonList(testEntity);

        given(repository.findAll()).willReturn(facilityEntities);

        //act
        List<Facility> facilities = facilityService.getFacilities();
        //assert
        Assertions.assertEquals(facilities, getTestData());
    }

    @Test
    void test_getFacilities_givingNullFacilities() {
        // arrange
        given(repository.findAll()).willReturn(null);

        // act and assert
        Assertions.assertThrows(FacilityNotFoundException.class, () -> facilityService.getFacilities());
    }

    @Test
    void test_getFacilities_givingEmptyFacilities() {
        // arrange
        given(repository.findAll()).willReturn(Collections.emptyList());

        // act and assert
        Assertions.assertThrows(FacilityNotFoundException.class, () -> facilityService.getFacilities());
    }

    private List<Facility> getTestData() {
        List<Facility> facilities = new ArrayList<>();
        Facility facility = new Facility();
        facility.setId(101);
        facility.setName("p1");
        facility.setAddress("testAddress");
        facilities.add(facility);
        return facilities;
    }
}