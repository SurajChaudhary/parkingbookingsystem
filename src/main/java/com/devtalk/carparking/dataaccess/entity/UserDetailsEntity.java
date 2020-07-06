package com.devtalk.carparking.dataaccess.entity;

import com.devtalk.carparking.model.UserInfoDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "PHONE_NUMBER")
    private long phoneNumber;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "LONGITUDE")
    private String longitude;

    @Column(name = "LATITUDE")
    private String latitude;

    @Column(name = "DRIVING_LICENSE_NUMBER")
    private String drivingLicenseNumber;

    @Column(name = "AADHAR_NUMBER")
    private String aadharNumber;

    @Column(name = "DRIVING_LICENSE_IMG")
    private byte[] drivingLicenseImage;
    @Column(name = "AADHAR_IMAGE")
    private byte[] aadharCardImage;
    @Column(name = "USER_IMAGE")
    private byte[] userImage;

    public UserInfoDetails getCurrentUser() {
        UserInfoDetails user = new UserInfoDetails();
        user.setUserName(this.getUserName());
        user.setEmailId(this.getEmailId());
        user.setPhoneNumber(this.getPhoneNumber());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setAddress(this.getAddress());
        user.setLongitude(this.getLongitude());
        user.setLatitude(this.getLatitude());
        user.setDrivingLicenseNumber(this.getDrivingLicenseNumber());
        user.setAadharNumber(this.getAadharNumber());
        user.setDrivingLicenseImage(this.getDrivingLicenseImage());
        user.setAadharCardImage(this.getAadharCardImage());
        user.setUserImage(this.getUserImage());
        return user;
    }


    public static UserInfoDetails getUser(UserDetailsEntity userEntity) {
        UserInfoDetails user = new UserInfoDetails();
        user.setUserName(userEntity.getUserName());
        user.setEmailId(userEntity.getEmailId());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setAddress(userEntity.getAddress());
        user.setLongitude(userEntity.getLongitude());
        user.setLatitude(userEntity.getLatitude());
        user.setDrivingLicenseNumber(userEntity.getDrivingLicenseNumber());
        user.setAadharNumber(userEntity.getAadharNumber());
        user.setDrivingLicenseImage(userEntity.getDrivingLicenseImage());
        user.setAadharCardImage(userEntity.getAadharCardImage());
        user.setUserImage(userEntity.getUserImage());
        return user;
    }

    public static UserDetailsEntity getUserEntity(UserInfoDetails user) {
        UserDetailsEntity entity = new UserDetailsEntity();
        entity.setUserName(user.getUserName());
        entity.setEmailId(user.getEmailId());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setAddress(user.getAddress());
        entity.setLongitude(user.getLongitude());
        entity.setLatitude(user.getLatitude());
        entity.setDrivingLicenseNumber(user.getDrivingLicenseNumber());
        entity.setAadharNumber(user.getAadharNumber());
        entity.setDrivingLicenseImage(user.getDrivingLicenseImage());
        entity.setAadharCardImage(user.getAadharCardImage());
        entity.setUserImage(user.getUserImage());
        return entity;

    }
}
