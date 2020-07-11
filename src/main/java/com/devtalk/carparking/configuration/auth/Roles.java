package com.devtalk.carparking.configuration.auth;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Roles {
    CUSTOMER(Sets.newHashSet(Permissions.CUSTOMER_READ,
            Permissions.CUSTOMER_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(Permissions.ADMIN_READ,
            Permissions.CUSTOMER_READ)),
    ADMIN(Sets.newHashSet(Permissions.CUSTOMER_READ,
            Permissions.CUSTOMER_WRITE,
            Permissions.ADMIN_READ,
            Permissions.ADMIN_WRITE));

    private final Set<Permissions> permissions;

    Roles(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
