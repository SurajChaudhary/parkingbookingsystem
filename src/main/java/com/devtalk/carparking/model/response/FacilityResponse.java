package com.devtalk.carparking.model.response;

import com.devtalk.carparking.model.Facility;
import lombok.Data;

import java.util.List;

@Data
public class FacilityResponse {
    List<Facility> facilities;
}
