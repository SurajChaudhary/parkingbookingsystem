package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dataaccess.entity.UserInfoDetailsEntity;
import com.devtalk.carparking.dataaccess.repository.UserInfoDetailsRepository;
import com.devtalk.carparking.model.UserInfoDetails;
import com.devtalk.carparking.service.UserInfoDetailsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class TestUserInfoDetailsServiceImpl {

    private UserInfoDetailsService userInfoDetailsService;
    private UserInfoDetailsRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(UserInfoDetailsRepository.class);
        userInfoDetailsService = new UserInfoDetailsServiceImpl(repository);
    }


    @Test
    void getAllUsers() {
        fail();
    }

    @Test
    void getUserDetails() {
        fail();
    }

    @Test
    void createUsers() {
        fail();
    }

    @Test
    void createUser() {
        //Assemble
        UserInfoDetails input = getUserDetailsEntity("Test User", "user.test@bookmyslot.com");
        UserInfoDetailsEntity inputEntity = UserInfoDetailsEntity.getUserEntity(input);
        given(repository.save(Mockito.any(UserInfoDetailsEntity.class))).willReturn(inputEntity);

        //Act
        String user = userInfoDetailsService.createUser(input);
        //Assert
        Assertions.assertThat(inputEntity.getUserName()).isEqualTo(user);
    }

    @Test
    void deleteUser() {
        fail();
    }

    @Test
    void updateUser() {
        fail();
    }

    private UserInfoDetails getUserDetailsEntity(String userName, String userEmail) {
        String[] names = userName.split(" ");
        return new UserInfoDetails(
                userName,
                userEmail,
                1234567890,
                names[0],
                names[1],
                "test address",
                "test longitude",
                "test latitude",
                "DL123456DGV",
                "AA1234GHF",
                null,
                null,
                null
        );
    }
}