package com.example.sty_backend_def.domains.models.exercise.dtos;

import jakarta.validation.constraints.NotBlank;

public record ExerciseSetRequestDto(
        @NotBlank
        Integer setIndex,

        String reps,
        String weight,
        String timeValue,
        String distanceValue,
        String technique
) {
}
