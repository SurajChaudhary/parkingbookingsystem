package com.devtalk.carparking.dao.entity;

import com.devtalk.carparking.model.seeddata.City;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CITIES")
@Data
public class CityEnitity {

    @Id
    private Long id;

    @Column(name = "NAME")
    private String  name;

    @OneToMany(mappedBy = "id")
    @Column(name = "STATE_ID")
    private StateEntity  stateEntity;


    private static City getCityFromCityEntity(CityEnitity cityEnitity){
        City city = new City();

        city.setId(cityEnitity.getId());
        city.setName(cityEnitity.getName());
        city.setState(StateEntity.getStateFromStateEntity(cityEnitity.getStateEntity()));
        return city;
    }

    private static CityEnitity getCityEntityFromCity(City city){
        CityEnitity cityEnitity = new CityEnitity();
        cityEnitity.setName(city.getName());
        cityEnitity.setStateEntity(StateEntity.getStateEntityFromStateModel(city.getState()));
        return cityEnitity;
    }
}
