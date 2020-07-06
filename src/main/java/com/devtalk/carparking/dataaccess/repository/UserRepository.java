package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.emailId = :emailId")
    UserEntity getUserByEmailId(@Param("emailId") String emailId);
}
