package com.example.sty_backend_def.domains.models.history;

import com.example.sty_backend_def.domains.models.workout.Workout;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public record HistoryRequestDto(
        @NotNull
        UUID id,

        @NotBlank
        Workout workout,

        @NotBlank
        LocalDate completionDate,

        @NotBlank
        LocalTime completionTime,

        @NotBlank
        Long durationMillis,

        @NotNull
        UUID userId,

        UUID workoutId

      //  @NotBlank
       // List<HistoryExerciseRequestDto> historyExerciseRequestDtos
) {}
