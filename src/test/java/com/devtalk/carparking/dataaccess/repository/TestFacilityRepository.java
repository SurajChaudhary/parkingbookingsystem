package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.CityEntity;
import com.devtalk.carparking.dataaccess.entity.FacilityEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
class TestFacilityRepository {

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void test_getFacilities_givingFacilities() throws Exception {
        // arrange
        FacilityEntity testData = new FacilityEntity(1, "testFacility", "testAddress", "longitude", "latitude", new CityEntity(1, 1, "testCity", 2));
        /*FacilityEntity facilityEntity = entityManager.merge(testData);
        entityManager.flush();*/
        facilityRepository.saveAndFlush(testData);

        // act
        List<FacilityEntity> entityList = facilityRepository.findAll();

        // assert
        Assertions.assertThat(entityList.get(0).getName()).isEqualTo(testData.getName());

    }

    @Test
    public void test_getFacilitiesByName_givingFacility() throws Exception {
        // arrange
        FacilityEntity testData = new FacilityEntity(1, "testFacility123", "testAddress123", "longitude123", "latitude123", new CityEntity(1, 1, "testCity123", 2));
        facilityRepository.saveAndFlush(testData);

        // act
        FacilityEntity facility = facilityRepository.findByName("testFacility123");

        // assert
        Assertions.assertThat(facility.getName()).isEqualTo(testData.getName());

    }

    @Test
    public void test_getFacilitiesByName_notGivingFacility() throws Exception {
        // arrange
        FacilityEntity testData = new FacilityEntity(1, "testFacility123", "testAddress123", "longitude123", "latitude123", new CityEntity(1, 1, "testCity123", 2));
        facilityRepository.saveAndFlush(testData);

        // act
        FacilityEntity facility = facilityRepository.findByName("testFacility124");

        // assert
        Assertions.assertThat(facility).isNull();

    }

    @AfterEach
    public void destroy() {
        facilityRepository.deleteAll();
    }

}