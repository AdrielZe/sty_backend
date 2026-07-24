package com.example.sty_backend_def.domains.models.user;

public record RegisterRequestDto(String id, String name, String email, String password, UserRole role) {
}
