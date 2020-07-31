package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dataaccess.entity.UserInfoDetailsEntity;
import com.devtalk.carparking.dataaccess.repository.UserInfoDetailsRepository;
import com.devtalk.carparking.model.UserInfoDetails;
import com.devtalk.carparking.service.UserInfoDetailsService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserInfoDetailsServiceImpl implements UserInfoDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoDetailsServiceImpl.class.getName());

    private final UserInfoDetailsRepository userInfoDetailsRepository;

    @Autowired
    public UserInfoDetailsServiceImpl(UserInfoDetailsRepository userInfoDetailsRepository) {
        this.userInfoDetailsRepository = userInfoDetailsRepository;
    }

    @Override
    public Optional<List<UserInfoDetails>> getAllUsers() {
        List<UserInfoDetailsEntity> allUserDetailsEntities = userInfoDetailsRepository.findAll();
        if (CollectionUtils.isEmpty(allUserDetailsEntities)) {
            return Optional.empty();
        }
        List<UserInfoDetails> users = allUserDetailsEntities
                .parallelStream()
                .map(UserInfoDetailsEntity::getUser)
                .collect(toList());
        return Optional.of(users);
    }

    @Override
    public Optional<UserInfoDetails> getUserDetails(int userId) {
        Optional<UserInfoDetailsEntity> userDetailsRepositoryById = userInfoDetailsRepository.findById(userId);
        if (userDetailsRepositoryById.isPresent()) {
            UserInfoDetailsEntity entity = userDetailsRepositoryById.get();
            return Optional.of(UserInfoDetailsEntity.getUser(entity));
        }
        return Optional.empty();
    }

    @Override
    public List<String> createUsers(List<UserInfoDetails> users) {
        List<UserInfoDetailsEntity> userDetailsEntities = users.parallelStream().map(UserInfoDetailsEntity::getUserEntity).collect(toList());
        Iterable<UserInfoDetailsEntity> entities = userInfoDetailsRepository.saveAll(userDetailsEntities);
        Iterator<UserInfoDetailsEntity> iterator = entities.iterator();
        List<String> savedEntities = new ArrayList<>();
        while (iterator.hasNext()) {
            UserInfoDetailsEntity userInfoEntity = iterator.next();
            savedEntities.add(String.valueOf(userInfoEntity.getId()));
        }
        return savedEntities;
    }

    @Override
    public String createUser(UserInfoDetails user) {
        UserInfoDetailsEntity entity = UserInfoDetailsEntity.getUserEntity(user);
        UserInfoDetailsEntity userInfoEntity = userInfoDetailsRepository.save(entity);
        return userInfoEntity.getUserName();
    }

    @Override
    public String deleteUser(int userId) throws Exception {
        Optional<UserInfoDetailsEntity> userDetailsRepositoryById = userInfoDetailsRepository.findById(userId);
        userDetailsRepositoryById.ifPresent(userInfoDetailsRepository::delete);
        UserInfoDetailsEntity entity = userDetailsRepositoryById.orElseThrow(() -> new Exception("No such user is found to delete!!!"));
        return String.valueOf(entity.getId());
    }

    @Override
    public Optional<UserInfoDetails> updateUser(int userId, UserInfoDetails user) throws Exception {
        Optional<UserInfoDetailsEntity> userDetailsRepositoryById = userInfoDetailsRepository.findById(userId);
        userDetailsRepositoryById.ifPresent(userInfoDetailsRepository::save);
        Optional<UserInfoDetailsEntity> updatedUserDetailsRepositoryById = userInfoDetailsRepository.findById(userId);
        UserInfoDetailsEntity entity = updatedUserDetailsRepositoryById.orElseThrow(() -> new Exception("No such user is found to update!!!"));
        return Optional.of(UserInfoDetailsEntity.getUser(entity));
    }
}
