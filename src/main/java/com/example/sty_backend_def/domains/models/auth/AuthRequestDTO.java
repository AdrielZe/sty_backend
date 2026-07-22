package com.example.sty_backend_def.domains.models.auth;

import jakarta.validation.constraints.NotNull;

public record AuthRequestDTO(
        @NotNull
        String email,

        @NotNull
        String password
){}
