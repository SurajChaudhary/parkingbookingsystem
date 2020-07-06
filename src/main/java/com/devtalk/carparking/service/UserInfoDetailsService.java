package com.devtalk.carparking.service;

import com.devtalk.carparking.model.UserInfoDetails;

import java.util.List;
import java.util.Optional;

public interface UserInfoDetailsService {

    Optional<List<UserInfoDetails>> getAllUsers();

    Optional<UserInfoDetails> getUserDetails(int userId);

    List<String> createUsers(List<UserInfoDetails> users);

    String createUser(UserInfoDetails user);

    String deleteUser(int userId) throws Exception;

    Optional<UserInfoDetails> updateUser(int userId, UserInfoDetails user) throws Exception;
}
