package com.devtalk.carparking.configuration.auth;

import com.devtalk.carparking.model.request.ApplicationUserRequest;
import com.devtalk.carparking.model.response.ApplicationUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao;

    @Autowired
    public ApplicationUserService(ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return applicationUserDao
                .getApplicationUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User " + userName + " could not be found."));
    }

    public List<ApplicationUserResponse> addNewUsersToSystem(List<ApplicationUserRequest> applicationUsers) {
        List<ApplicationUser> userDetails = applicationUserDao.addNewUsersToSystem(applicationUsers);
        return userDetails.stream().map(ApplicationUser::getApplicationUserResponse).collect(Collectors.toList());
    }
}
