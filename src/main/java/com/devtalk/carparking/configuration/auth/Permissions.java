package com.devtalk.carparking.configuration.auth;

public enum Permissions {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
