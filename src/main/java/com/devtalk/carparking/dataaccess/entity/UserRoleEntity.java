package com.devtalk.carparking.dataaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS_ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "ROLE")
    private String role;
}
