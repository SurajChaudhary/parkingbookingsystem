package com.devtalk.carparking.configuration.auth;

import java.util.Optional;

public interface ApplicationUserDao {
    Optional<ApplicationUser> getApplicationUserByUserName(String userName);
}
