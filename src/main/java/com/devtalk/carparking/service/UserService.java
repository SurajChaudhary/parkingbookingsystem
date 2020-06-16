package com.devtalk.carparking.service;

import com.devtalk.carparking.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<List<User>> getAllUsers();
    Optional<User> getUserDetails(int userId);
    List<String> createUsers(List<User> users);
    String createUser(User user);
    String deleteUser(int userId) throws Exception;
    Optional<User> updateUser(int userId, User user) throws Exception;
}
