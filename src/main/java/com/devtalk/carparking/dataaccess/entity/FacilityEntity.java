package com.devtalk.carparking.dataaccess.entity;

import com.devtalk.carparking.model.Facility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityEntity {

    private int id;
    private String name;
    private String address;

    public static Facility getFacility(FacilityEntity facilityEntity) {
        Facility facility = new Facility();
        facility.setId(facilityEntity.getId());
        facility.setName(facilityEntity.getName());
        facility.setAddress(facilityEntity.getAddress());
        return facility;
    }
}
