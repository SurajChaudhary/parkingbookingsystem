package com.devtalk.carparking.dataaccess.entity;

import com.devtalk.carparking.model.request.PermissionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS_PERMISSIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "PERMISSION_ID")
    private long permissionId;
    @Column(name = "PERMISSION_NAME")
    private String permissionName;
    @Column(name = "ROLE_ID")
    private long roleId;

    public static PermissionEntity getEntityFromModel(PermissionRequest permissionRequest) {
        PermissionEntity entity = new PermissionEntity();
        entity.setPermissionId(permissionRequest.getPermissionId());
        entity.setPermissionName(permissionRequest.getPermissionName());
        return entity;
    }
}
