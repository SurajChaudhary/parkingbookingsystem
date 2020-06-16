package com.devtalk.carparking.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SeedDataService {
    public void addStates(List<String> states) {
        return;
    }

    public List<String> getStates() {
        return Arrays.asList("Ghaziabad","Delhi","Bangalore");
    }
}
