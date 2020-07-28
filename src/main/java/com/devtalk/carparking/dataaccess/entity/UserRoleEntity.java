package com.devtalk.carparking.dataaccess.entity;

import com.devtalk.carparking.model.request.UserRoleRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "USER_ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "ROLE_ID")
    private long roleId;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "USER_EMAIL")
    private String userEmail;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    private Set<PermissionEntity> permissions;

    public static UserRoleEntity getEntityFromModel(UserRoleRequest userRoleRequest) {
        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRoleId(userRoleRequest.getRoleId());
        roleEntity.setRoleName(userRoleRequest.getRoleName());
        if(CollectionUtils.isNotEmpty(userRoleRequest.getPermissions())) {
            Set<PermissionEntity> permissions = userRoleRequest
                    .getPermissions()
                    .stream()
                    .map(PermissionEntity::getEntityFromModel)
                    .collect(Collectors.toSet());
            roleEntity.setPermissions(permissions);
        }
        return roleEntity;
    }
}
