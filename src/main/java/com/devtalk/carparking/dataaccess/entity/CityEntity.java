package com.devtalk.carparking.dataaccess.entity;

import com.devtalk.carparking.model.request.CityRequest;
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
    private long id;

    @Column(name = "CITY_ID")
    private long cityId;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "STATE_ID")
    private long stateId;


    public static City getCityFromCityEntity(CityEntity cityEntity) {
        City city = new City();
        city.setId(cityEntity.getCityId());
        city.setName(cityEntity.getCityName());
        city.setStateId(cityEntity.getStateId());
        return city;
    }

    public static CityEntity getEntityFromCityRequest(CityRequest city) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setCityId(city.getCityId());
        cityEntity.setCityName(city.getCityName());
        return cityEntity;
    }
}
