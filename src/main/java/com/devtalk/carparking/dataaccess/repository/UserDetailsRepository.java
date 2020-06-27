package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.UserInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDetailsRepository extends CrudRepository<UserInfoEntity, Integer> {

}
