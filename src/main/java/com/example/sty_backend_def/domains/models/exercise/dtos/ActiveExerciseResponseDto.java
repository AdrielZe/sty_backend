package com.example.sty_backend_def.domains.models.exercise.dtos;

import com.example.sty_backend_def.domains.models.MuscleGroup;
import com.example.sty_backend_def.domains.models.exercise.ExerciseType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ActiveExerciseResponseDto (
        String name,
        ExerciseType type,
        String timeValue,
        String distanceValue,
        MuscleGroup muscleGroup,
        Boolean isCompleted,
        Integer setsCompleted,
        Integer weightRecord,
        List<ExerciseSetResponseDto> sets
){}
