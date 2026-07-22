package com.example.sty_backend_def.domains.models.exercise.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ExerciseRequestDto(
        @NotNull(message = "The id can`t be null")
        UUID id,

        @NotBlank(message = "The exercise name is required")
        String exerciseName,

        @NotBlank(message = "The exercise type is required" )
        String exerciseType,

        @NotBlank(message = "The muscle group is required")
        String muscleGroup
){

}