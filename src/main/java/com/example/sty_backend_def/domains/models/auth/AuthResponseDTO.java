package com.example.sty_backend_def.domains.models.auth;

import java.util.UUID;

public record AuthResponseDTO(
        UUID id,
        String name
) {}
