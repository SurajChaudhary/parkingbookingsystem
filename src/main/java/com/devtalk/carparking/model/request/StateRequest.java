package com.devtalk.carparking.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateRequest {
    private int stateId;
    private String stateName;
    private List<CityRequest> cities;
}
