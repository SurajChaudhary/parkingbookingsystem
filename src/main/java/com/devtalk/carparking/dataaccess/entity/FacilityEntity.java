package com.devtalk.carparking.dataaccess.entity;

import com.devtalk.carparking.model.Facility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PARKING_FACILITIES")
public class FacilityEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "LONGITUDE")
    private String longitude;
    @Column(name = "LATITUDE")
    private String latitude;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
    private CityEntity cityId;


    public static Facility getFacility(FacilityEntity facilityEntity) {
        Facility facility = new Facility();
        facility.setId(facilityEntity.getId());
        facility.setName(facilityEntity.getName());
        facility.setAddress(facilityEntity.getAddress());
        return facility;
    }
}
