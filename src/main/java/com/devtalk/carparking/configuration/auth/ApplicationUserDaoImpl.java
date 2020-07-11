package com.devtalk.carparking.configuration.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDaoImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> getApplicationUserByUserName(String userName) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> userName.equalsIgnoreCase(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Arrays.asList(
                new ApplicationUser(
                        Roles.ADMIN.grantedAuthorities(),
                        "admin",
                        passwordEncoder.encode("admin"),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(
                        Roles.ADMIN_TRAINEE.grantedAuthorities(),
                        "tom",
                        passwordEncoder.encode("tom"),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(
                        Roles.CUSTOMER.grantedAuthorities(),
                        "bob",
                        passwordEncoder.encode("bob"),
                        true,
                        true,
                        true,
                        true)
        );
        return applicationUsers;
    }
}
