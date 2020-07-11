package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.CityEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void findAllByStateName() {
        CityEntity city = new CityEntity(1, 1, "New Delhi", 1);
        cityRepository.saveAndFlush(city);
        CityEntity delhi = cityRepository.findByCityName("New Delhi");
        Assertions.assertThat(delhi.getCityName()).isEqualTo(city.getCityName());
    }

    @AfterEach
    public void destroy() {
        cityRepository.deleteAll();
    }
}