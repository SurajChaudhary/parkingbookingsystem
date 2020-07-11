package com.devtalk.carparking.model.seeddata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private long id;
    private String name;
    private long stateId;
}
