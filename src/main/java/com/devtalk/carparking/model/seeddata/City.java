package com.devtalk.carparking.model.seeddata;

import lombok.Data;

@Data
public class City {
    private Long id;
    private String name;
    private State state;
}
