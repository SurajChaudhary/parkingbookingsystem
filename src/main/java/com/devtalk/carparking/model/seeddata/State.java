package com.devtalk.carparking.model.seeddata;

import com.devtalk.carparking.dataaccess.entity.CityEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class State {
    private long id;
    private long stateId;
    private String stateName;
    private List<CityEntity> cities;
}
