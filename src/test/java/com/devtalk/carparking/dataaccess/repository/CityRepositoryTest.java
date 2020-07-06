package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.CityEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void findAllByStateName() {
        CityEntity cities = new CityEntity(1, "New Delhi", "Delhi");
        cityRepository.saveAndFlush(cities);
        List<CityEntity> delhi = cityRepository.findAllByStateName("Delhi");
        Assertions.assertThat(delhi.get(0).getName()).isEqualTo(cities.getName());
    }

    @AfterEach
    public void destroy() {
        cityRepository.deleteAll();
    }
}