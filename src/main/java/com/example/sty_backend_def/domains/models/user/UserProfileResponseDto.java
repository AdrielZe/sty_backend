package com.example.sty_backend_def.domains.models.user;

import lombok.Builder;

@Builder
public record UserProfileResponseDto(
        String username,
        Integer weeklyGoal,
        String picture
) {
}
