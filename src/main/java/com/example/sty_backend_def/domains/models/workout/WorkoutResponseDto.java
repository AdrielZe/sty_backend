package com.example.sty_backend_def.domains.models.workout;

import com.example.sty_backend_def.domains.models.exercise.Exercise;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record WorkoutResponseDto(
        UUID workoutId,
        UUID userId,
        String workoutName,
        String dayOfWeek,
        List<Exercise> exercises
) {
}
