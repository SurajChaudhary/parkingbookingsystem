package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.CityEntity;
import com.devtalk.carparking.dataaccess.entity.FacilityEntity;
import org.assertj.core.api.Assertions;
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
        FacilityEntity facilityEntity = entityManager.merge(new FacilityEntity(1, "testFacility", "testAddress","longitude","latitude",new CityEntity(1,"testCity","Karnataka")));
        entityManager.flush();
        //FacilityEntity facilityEntity = facilityRepository.saveAndFlush(new FacilityEntity(1, "testFacility", "testAddress", "longitude", "latitude", 2));

        // act
        List<FacilityEntity> entityList = facilityRepository.findAll();

        // assert
        Assertions.assertThat(entityList.get(0).getName()).isEqualTo(facilityEntity.getName());

    }

}