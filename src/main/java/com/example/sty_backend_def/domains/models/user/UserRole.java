package com.example.sty_backend_def.domains.models.user;

public enum UserRole {
    USER("user"),
    PREMIUM_USER("premium_user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
