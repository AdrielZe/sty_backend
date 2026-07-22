package com.example.sty_backend_def.domains.models.user;

import java.util.UUID;

public record EmailRequestDto(
        UUID userId,
        String email
) {
}
