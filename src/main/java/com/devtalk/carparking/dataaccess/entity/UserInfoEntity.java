package com.devtalk.carparking.dataaccess.entity;

import com.devtalk.carparking.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "USER_INFO")
@Data
@NoArgsConstructor
public class UserInfoEntity implements Serializable {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "EMAIL_ID")
    private String emailId;
    @Column(name = "PHONE_NUMBER")
    private BigDecimal phoneNumber;
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
    private String driverLicenseNumber;
    @Column(name = "AADHAR_NUMBER")
    private String aadharCardNumber;
    @Column(name = "DRIVING_LICENSE_IMG")
    private byte[] drivingLicenseImage;
    @Column(name = "AADHAR_IMAGE")
    private byte[] aadharCardImage;
    @Column(name = "USER_IMAGE")
    private byte[] userImage;

    public User getCurrentUser() {
        User user = new User();
        user.setUserName(this.getUserName());
        user.setEmailId(this.getEmailId());
        user.setPhoneNumber(this.getPhoneNumber());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setAddress(this.getAddress());
        user.setLongitude(this.getLongitude());
        user.setLatitude(this.getLatitude());
        user.setDrivingLicenseNumber(this.getDriverLicenseNumber());
        user.setAadharNumber(this.getAadharCardNumber());
        user.setDrivingLicenseImage(this.getDrivingLicenseImage());
        user.setAadharCardImage(this.getAadharCardImage());
        user.setUserImage(this.getUserImage());
        return user;
    }


    public static User getUser(UserInfoEntity userEntity) {
        User user = new User();
        user.setUserName(userEntity.getUserName());
        user.setEmailId(userEntity.getEmailId());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setAddress(userEntity.getAddress());
        user.setLongitude(userEntity.getLongitude());
        user.setLatitude(userEntity.getLatitude());
        user.setDrivingLicenseNumber(userEntity.getDriverLicenseNumber());
        user.setAadharNumber(userEntity.getAadharCardNumber());
        user.setDrivingLicenseImage(userEntity.getDrivingLicenseImage());
        user.setAadharCardImage(userEntity.getAadharCardImage());
        user.setUserImage(userEntity.getUserImage());
        return user;
    }

    public static UserInfoEntity getUserEntity(User user) {
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUserName(user.getUserName());
        entity.setEmailId(user.getEmailId());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setAddress(user.getAddress());
        entity.setLongitude(user.getLongitude());
        entity.setLatitude(user.getLatitude());
        entity.setDriverLicenseNumber(user.getDrivingLicenseNumber());
        entity.setAadharCardNumber(user.getAadharNumber());
        entity.setDrivingLicenseImage(user.getDrivingLicenseImage());
        entity.setAadharCardImage(user.getAadharCardImage());
        entity.setUserImage(user.getUserImage());
        return entity;

    }
}
