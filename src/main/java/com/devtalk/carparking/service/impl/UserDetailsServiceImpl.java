package com.devtalk.carparking.service.impl;

import com.devtalk.carparking.dataaccess.entity.RoleEntity;
import com.devtalk.carparking.dataaccess.entity.UserEntity;
import com.devtalk.carparking.dataaccess.repository.UserRepository;
import com.devtalk.carparking.exception.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String emailId)
            throws UsernameNotFoundException {
        //UserEntity userEntity = userRepository.getUserByEmailId(emailId);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmailId("admin@admin.com");
        userEntity.setPassword("admin");
        userEntity.setEnabled(true);
        userEntity.setId(1);
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity role = new RoleEntity();
        role.setId(1);
        role.setName("ADMIN");
        roles.add(role);
        userEntity.setRoles(roles);
        if (userEntity == null) {
            throw new UsernameNotFoundException();
        }

        return new MyUserDetails(userEntity);
    }
}
