package com.devtalk.carparking.dataaccess.repository;

import com.devtalk.carparking.dataaccess.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    List<UserRoleEntity> findUserRoleEntitiesByRoleIdIn(Set<Long> roleIds);

    UserRoleEntity findUserRoleEntityByRoleId(long roleId);
}
