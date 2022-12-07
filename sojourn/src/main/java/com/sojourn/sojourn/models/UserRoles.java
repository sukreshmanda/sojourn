package com.sojourn.sojourn.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    USER("USER");

    UserRoles(String roleName) {
        this.roleName = roleName;
    }
    private String roleName;
    @Override
    public String getAuthority() {
        return roleName;
    }
}
