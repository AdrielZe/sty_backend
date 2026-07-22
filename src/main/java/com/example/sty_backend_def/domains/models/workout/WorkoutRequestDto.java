package com.example.sty_backend_def.domains.models.workout;

import com.example.sty_backend_def.domains.models.exercise.Exercise;
import com.example.sty_backend_def.domains.models.user.User;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record WorkoutRequestDto(
        @NotNull
        UUID workoutId,

        @NotNull
        String workoutName,

        String dayOfWeek,

        @NotNull
        UUID userId,

        List<Exercise> exercises
) {

}
