package com.example.sty_backend_def.domains.models.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserWeeklyGoalRequestDto(
        @NotNull @Min(1) @Max(7)
        Integer weeklyGoal
) { }
