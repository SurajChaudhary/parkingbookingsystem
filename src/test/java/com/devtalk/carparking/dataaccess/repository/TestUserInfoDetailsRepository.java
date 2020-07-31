package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.UserInfoDetailsEntity;
import com.devtalk.carparking.model.UserInfoDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
class TestUserInfoDetailsRepository {

    @Autowired
    private UserInfoDetailsRepository userInfoDetailsRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void test_saveUserDetails_savingUserDetails() throws Exception {
        // arrange
        UserInfoDetailsEntity inputEntity = getUserDetailsEntity("Test User", "user.test@bookmyslot.com");
        userInfoDetailsRepository.saveAndFlush(inputEntity);

        // act
        List<UserInfoDetailsEntity> entityList = userInfoDetailsRepository.findAll();

        // assert
        Assertions.assertThat(entityList.get(0).getUserName()).isEqualTo(inputEntity.getUserName());

    }

    @AfterEach
    public void destroy() {
        userInfoDetailsRepository.deleteAll();
    }

    private UserInfoDetailsEntity getUserDetailsEntity(String userName, String userEmail) {
        String[] names = userName.split(" ");
        UserInfoDetails userInfoDetails = new UserInfoDetails(
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
        return UserInfoDetailsEntity.getUserEntity(userInfoDetails);
    }

}