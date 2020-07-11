package com.devtalk.carparking.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {

    private int cityId;
    private String cityName;
    private int stateId;
}
