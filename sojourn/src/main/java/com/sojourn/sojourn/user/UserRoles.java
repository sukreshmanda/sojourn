package com.sojourn.sojourn.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN");

    UserRoles(String roleName) {
        this.roleName = roleName;
    }
    private final String roleName;
    @Override
    public String getAuthority() {
        return roleName;
    }
}
