package com.devtalk.carparking.dao.repository;

import com.devtalk.carparking.dao.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SeedDataRepository extends JpaRepository<UserInfoEntity, Integer> {

}
