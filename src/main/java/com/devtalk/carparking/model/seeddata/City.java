package com.devtalk.carparking.model.seeddata;

import lombok.Data;

@Data
public class City {
    private Integer id;
    private String name;
    private State state;
}
