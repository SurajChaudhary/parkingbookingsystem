package com.devtalk.carparking.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequest {

    private long roleId;
    private String roleName;
    private Set<PermissionRequest> permissions;
}
