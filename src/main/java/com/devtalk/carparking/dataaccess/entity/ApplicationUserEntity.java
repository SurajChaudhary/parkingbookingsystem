package com.devtalk.carparking.dataaccess.entity;

import com.devtalk.carparking.configuration.auth.ApplicationUser;
import com.devtalk.carparking.model.request.ApplicationUserRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "APPLICATION_USERS")
@Data
@NoArgsConstructor
public class ApplicationUserEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "ACCOUNT_NON_EXPIRED")
    private boolean isAccountNonExpired;
    @Column(name = "ACCOUNT_NON_LOCKED")
    private boolean isAccountNonLocked;
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private boolean isCredentialsNonExpired;
    @Column(name = "ENABLED")
    private boolean isEnabled;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_EMAIL", referencedColumnName = "USER_EMAIL")
    private Set<UserRoleEntity> userRoles;

    public static ApplicationUserEntity getEntityFromRequest(ApplicationUserRequest applicationUserRequest) {
        ApplicationUserEntity userEntity = new ApplicationUserEntity();
        userEntity.setUserName(applicationUserRequest.getUserName());
        userEntity.setPassword(applicationUserRequest.getPassword());
        userEntity.setUserEmail(applicationUserRequest.getUserEmail());
        userEntity.setAccountNonExpired(applicationUserRequest.isAccountNonExpired());
        userEntity.setAccountNonLocked(applicationUserRequest.isAccountNonLocked());
        userEntity.setCredentialsNonExpired(applicationUserRequest.isCredentialsNonExpired());
        userEntity.setEnabled(applicationUserRequest.isEnabled());
        Set<UserRoleEntity> userRoleEntities = applicationUserRequest.getUserRoles().stream().map(UserRoleEntity::getEntityFromModel).collect(Collectors.toSet());
        userEntity.setUserRoles(userRoleEntities);
        return userEntity;
    }

    public static ApplicationUser getApplicationUserFromEntity(ApplicationUserEntity userEntity) {
        return new ApplicationUser(getGrantedAuthorities(userEntity.getUserRoles()),
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.isAccountNonExpired(),
                userEntity.isAccountNonLocked(),
                userEntity.isCredentialsNonExpired(),
                userEntity.isEnabled());
    }

    //Todo: Simplify nested loops : can we use flatMap here
    public static Set<SimpleGrantedAuthority> getGrantedAuthorities(Set<UserRoleEntity> userRoles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(userRoleEntity -> {
            Set<SimpleGrantedAuthority> grantedAuthorities = userRoleEntity.getPermissions()
                    .stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                    .collect(Collectors.toSet());
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRoleName()));
            authorities.addAll(grantedAuthorities);
        });
        return authorities;
    }
}
