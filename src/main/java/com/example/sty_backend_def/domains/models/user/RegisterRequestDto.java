package com.example.sty_backend_def.domains.models.user;

public record RegisterRequestDto(String login, String password, UserRole role) {
}
