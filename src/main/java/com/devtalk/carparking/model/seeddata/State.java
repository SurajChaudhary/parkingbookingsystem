package com.devtalk.carparking.model.seeddata;

import lombok.Data;

import java.util.List;

@Data
public class State {

    private Long id;
    private String stateName;
    private List<String> citiNames;
}
