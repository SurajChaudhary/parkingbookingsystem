package com.devtalk.carparking.configuration.auth;

import com.devtalk.carparking.dataaccess.entity.ApplicationUserEntity;
import com.devtalk.carparking.model.response.ApplicationUserResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


public class ApplicationUser implements UserDetails {

    private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final String userName;
    private final String password;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    public ApplicationUser(Set<? extends GrantedAuthority> grantedAuthorities,
                           String userName,
                           String password,
                           boolean isAccountNonExpired,
                           boolean isAccountNonLocked,
                           boolean isCredentialsNonExpired,
                           boolean isEnabled) {
        this.grantedAuthorities = grantedAuthorities;
        this.userName = userName;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public static ApplicationUserResponse getApplicationUserResponse(ApplicationUser applicationUser) {
        return new ApplicationUserResponse(
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                applicationUser.isAccountNonExpired(),
                applicationUser.isAccountNonLocked(),
                applicationUser.isCredentialsNonExpired(),
                applicationUser.isEnabled(),
                applicationUser.getAuthorities()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public static ApplicationUser getApplicationUser(ApplicationUserEntity userEntity) {
        return new ApplicationUser(
                ApplicationUserEntity.getGrantedAuthorities(userEntity.getUserRoles()),
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.isAccountNonExpired(),
                userEntity.isAccountNonLocked(),
                userEntity.isCredentialsNonExpired(),
                userEntity.isEnabled());
    }
}
