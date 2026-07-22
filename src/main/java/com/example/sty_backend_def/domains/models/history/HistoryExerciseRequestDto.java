package com.example.sty_backend_def.domains.models.history;

import com.example.sty_backend_def.domains.models.exercise.ExerciseType;
import com.example.sty_backend_def.domains.models.exercise.dtos.ExerciseSetRequestDto;
import com.example.sty_backend_def.domains.models.exercise.dtos.ExerciseSetResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record HistoryExerciseRequestDto(
        @NotNull(message = "Id field is required")
        UUID id,

        @NotBlank(message = "Name is required")
        String exerciseName,

        @NotBlank(message = "Exercise record is required")
        String exerciseRecord,

        @NotBlank(message = "Exercise type is required")
        ExerciseType exerciseType,

        @NotBlank(message = "At least 1 exercise set is required.")
        List<ExerciseSetRequestDto>  exerciseSetRequestDtos
) {
}
