package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dao.entity.UserInfoEntity;
import com.devtalk.carparking.dao.repository.UserDetailsRepository;
import com.devtalk.carparking.model.User;
import com.devtalk.carparking.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        List<UserInfoEntity> allUserDetailsEntities = (List<UserInfoEntity>) userDetailsRepository.findAll();
        if (CollectionUtils.isEmpty(allUserDetailsEntities)) {
            return Optional.empty();
        }
        List<User> users = allUserDetailsEntities
                .parallelStream()
                .map(UserInfoEntity::getUser)
                .collect(toList());
        return Optional.of(users);
    }

    @Override
    public Optional<User> getUserDetails(int userId) {
        Optional<UserInfoEntity> userDetailsRepositoryById = userDetailsRepository.findById(userId);
        if (userDetailsRepositoryById.isPresent()) {
            UserInfoEntity entity = userDetailsRepositoryById.get();
            return Optional.of(UserInfoEntity.getUser(entity));
        }
        return Optional.empty();
    }

    @Override
    public List<String> createUsers(List<User> users) {
        List<UserInfoEntity> userDetailsEntities = users.parallelStream().map(UserInfoEntity::getUserEntity).collect(toList());
        Iterable<UserInfoEntity> entities = userDetailsRepository.saveAll(userDetailsEntities);
        Iterator<UserInfoEntity> iterator = entities.iterator();
        List<String> savedEntities = new ArrayList<>();
        while (iterator.hasNext()) {
            UserInfoEntity userInfoEntity = iterator.next();
            savedEntities.add(String.valueOf(userInfoEntity.getId()));
        }
        return savedEntities;
    }

    @Override
    public String createUser(User user) {
        UserInfoEntity entity = UserInfoEntity.getUserEntity(user);
        UserInfoEntity userInfoEntity = userDetailsRepository.save(entity);
        return String.valueOf(userInfoEntity.getId());
    }

    @Override
    public String deleteUser(int userId) throws Exception {
        Optional<UserInfoEntity> userDetailsRepositoryById = userDetailsRepository.findById(userId);
        userDetailsRepositoryById.ifPresent(userDetailsRepository::delete);
        UserInfoEntity entity = userDetailsRepositoryById.orElseThrow(() -> new Exception("No such user is found to delete!!!"));
        return String.valueOf(entity.getId());
    }

    @Override
    public Optional<User> updateUser(int userId, User user) throws Exception {
        Optional<UserInfoEntity> userDetailsRepositoryById = userDetailsRepository.findById(userId);
        userDetailsRepositoryById.ifPresent(userDetailsRepository::save);
        Optional<UserInfoEntity> updatedUserDetailsRepositoryById = userDetailsRepository.findById(userId);
        UserInfoEntity entity = updatedUserDetailsRepositoryById.orElseThrow(() -> new Exception("No such user is found to update!!!"));
        return Optional.of(UserInfoEntity.getUser(entity));
    }
}
