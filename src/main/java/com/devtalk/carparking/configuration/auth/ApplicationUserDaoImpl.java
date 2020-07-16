package com.devtalk.carparking.configuration.auth;

import com.devtalk.carparking.dataaccess.entity.ApplicationUserEntity;
import com.devtalk.carparking.dataaccess.repository.ApplicationUsersRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUsersRepository applicationUsersRepository;

    @Autowired
    public ApplicationUserDaoImpl(PasswordEncoder passwordEncoder,
                                  ApplicationUsersRepository applicationUsersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUsersRepository = applicationUsersRepository;
    }

    @Override
    public Optional<ApplicationUser> getApplicationUserByUserName(String userName) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> userName.equalsIgnoreCase(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUserEntity> applicationUserEntities = applicationUsersRepository.findAll();
        if (CollectionUtils.isEmpty(applicationUserEntities)) {
            return getDummyUsers();
        }
        return applicationUserEntities
                .stream()
                .map(ApplicationUser::getApplicationUser)
                .collect(Collectors.toList());
    }

    private List<ApplicationUser> getDummyUsers() {
        return Arrays.asList(
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
    }
}
