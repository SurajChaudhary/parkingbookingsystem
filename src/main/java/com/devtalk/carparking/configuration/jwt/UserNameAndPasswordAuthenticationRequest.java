package com.devtalk.carparking.configuration.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNameAndPasswordAuthenticationRequest {
    private String userName;
    private String password;
}
