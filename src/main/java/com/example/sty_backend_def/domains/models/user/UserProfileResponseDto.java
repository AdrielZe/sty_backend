package com.example.sty_backend_def.domains.models.user;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserProfileResponseDto(
        UUID id,
        String username,
        Integer weeklyGoal,
        String picture
) {
}
