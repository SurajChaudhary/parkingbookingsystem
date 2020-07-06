package com.devtalk.carparking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDetails {
    private String userName;
    private String emailId;
    private long phoneNumber;
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
