package com.devtalk.carparking.dao.entity;

import com.devtalk.carparking.model.seeddata.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CITIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String  name;

    @Column(name = "STATE_NAME")
    private String  stateName;


    public static City getCityFromCityEntity(CityEntity cityEntity){
        City city = new City();
        city.setId(cityEntity.getId());
        city.setName(cityEntity.getName());
//        city.setState(StateEntity.getStateFromStateEntity(cityEntity.getStateEntity()));
        return city;
    }

    public static CityEntity getCityEntityFromCity(City city){
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(city.getName());
  //      cityEntity.setStateEntity(StateEntity.getStateEntityFromStateModel(city.getState()));
        return cityEntity;
    }
}
