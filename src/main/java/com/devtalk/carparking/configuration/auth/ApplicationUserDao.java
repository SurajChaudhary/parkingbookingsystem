package com.devtalk.carparking.configuration.auth;

import com.devtalk.carparking.model.request.ApplicationUserRequest;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserDao {
    Optional<ApplicationUser> getApplicationUserByUserName(String userName);

    List<ApplicationUser> addNewUsersToSystem(List<ApplicationUserRequest> applicationUsers);
}
