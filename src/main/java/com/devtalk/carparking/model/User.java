package com.devtalk.carparking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private String emailId;
    private BigDecimal phoneNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String longitude;
    private String latitude;
    private String drivingLicenseNumber;
    private String aadharNumber;
    private byte[] drivingLicenseImage;
    private byte[] aadharCardImage;
    private byte[] userImage;
}
