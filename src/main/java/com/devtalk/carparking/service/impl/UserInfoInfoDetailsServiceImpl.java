package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dataaccess.entity.UserDetailsEntity;
import com.devtalk.carparking.dataaccess.repository.UserInfoDetailsRepository;
import com.devtalk.carparking.model.UserInfoDetails;
import com.devtalk.carparking.service.UserInfoDetailsService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserInfoInfoDetailsServiceImpl implements UserInfoDetailsService {

    private final UserInfoDetailsRepository userInfoDetailsRepository;

    @Autowired
    public UserInfoInfoDetailsServiceImpl(UserInfoDetailsRepository userInfoDetailsRepository) {
        this.userInfoDetailsRepository = userInfoDetailsRepository;
    }

    @Override
    public Optional<List<UserInfoDetails>> getAllUsers() {
        List<UserDetailsEntity> allUserDetailsEntities = userInfoDetailsRepository.findAll();
        if (CollectionUtils.isEmpty(allUserDetailsEntities)) {
            return Optional.empty();
        }
        List<UserInfoDetails> users = allUserDetailsEntities
                .parallelStream()
                .map(UserDetailsEntity::getUser)
                .collect(toList());
        return Optional.of(users);
    }

    @Override
    public Optional<UserInfoDetails> getUserDetails(int userId) {
        Optional<UserDetailsEntity> userDetailsRepositoryById = userInfoDetailsRepository.findById(userId);
        if (userDetailsRepositoryById.isPresent()) {
            UserDetailsEntity entity = userDetailsRepositoryById.get();
            return Optional.of(UserDetailsEntity.getUser(entity));
        }
        return Optional.empty();
    }

    @Override
    public List<String> createUsers(List<UserInfoDetails> users) {
        List<UserDetailsEntity> userDetailsEntities = users.parallelStream().map(UserDetailsEntity::getUserEntity).collect(toList());
        Iterable<UserDetailsEntity> entities = userInfoDetailsRepository.saveAll(userDetailsEntities);
        Iterator<UserDetailsEntity> iterator = entities.iterator();
        List<String> savedEntities = new ArrayList<>();
        while (iterator.hasNext()) {
            UserDetailsEntity userInfoEntity = iterator.next();
            savedEntities.add(String.valueOf(userInfoEntity.getId()));
        }
        return savedEntities;
    }

    @Override
    public String createUser(UserInfoDetails user) {
        UserDetailsEntity entity = UserDetailsEntity.getUserEntity(user);
        UserDetailsEntity userInfoEntity = userInfoDetailsRepository.save(entity);
        return String.valueOf(userInfoEntity.getId());
    }

    @Override
    public String deleteUser(int userId) throws Exception {
        Optional<UserDetailsEntity> userDetailsRepositoryById = userInfoDetailsRepository.findById(userId);
        userDetailsRepositoryById.ifPresent(userInfoDetailsRepository::delete);
        UserDetailsEntity entity = userDetailsRepositoryById.orElseThrow(() -> new Exception("No such user is found to delete!!!"));
        return String.valueOf(entity.getId());
    }

    @Override
    public Optional<UserInfoDetails> updateUser(int userId, UserInfoDetails user) throws Exception {
        Optional<UserDetailsEntity> userDetailsRepositoryById = userInfoDetailsRepository.findById(userId);
        userDetailsRepositoryById.ifPresent(userInfoDetailsRepository::save);
        Optional<UserDetailsEntity> updatedUserDetailsRepositoryById = userInfoDetailsRepository.findById(userId);
        UserDetailsEntity entity = updatedUserDetailsRepositoryById.orElseThrow(() -> new Exception("No such user is found to update!!!"));
        return Optional.of(UserDetailsEntity.getUser(entity));
    }
}
