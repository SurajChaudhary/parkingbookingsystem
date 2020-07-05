package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.CityEntity;
import com.devtalk.carparking.dataaccess.entity.FacilityEntity;
import com.devtalk.carparking.dataaccess.entity.StateEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class StateRepositoryTest {

    @Autowired
    private StateRepository stateRepository;

    @Test
    void findAllByNameIn() {
        StateEntity testData = new StateEntity(1l, "testStateName", new ArrayList<>());
        stateRepository.saveAndFlush(testData);

        // act
        List<StateEntity> entityList = stateRepository.findAll();

        // assert
        Assertions.assertThat(entityList.get(0).getName()).isEqualTo(testData.getName());

    }

    @AfterEach
    public void destroy() {
        stateRepository.deleteAll();
    }

}