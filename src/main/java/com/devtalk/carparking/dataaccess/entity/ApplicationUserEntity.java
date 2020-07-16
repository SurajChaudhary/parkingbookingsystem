package com.devtalk.carparking.dataaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "APPLICATION_USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "ACCOUNT_NON_EXPIRED")
    private boolean isAccountNonExpired;
    @Column(name = "ACCOUNT_NON_LOCKED")
    private boolean isAccountNonLocked;
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private boolean isCredentialsNonExpired;
    @Column(name = "ENABLED")
    private boolean isEnabled;

}
