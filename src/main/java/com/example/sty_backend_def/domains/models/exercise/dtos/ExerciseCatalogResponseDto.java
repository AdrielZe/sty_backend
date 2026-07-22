package com.example.sty_backend_def.domains.models.exercise.dtos;


import com.example.sty_backend_def.domains.models.MuscleGroup;
import com.example.sty_backend_def.domains.models.exercise.ExerciseType;

import java.util.UUID;

public record ExerciseCatalogResponseDto (
        UUID id,
        String name,
        ExerciseType type,
        MuscleGroup muscleGroup
) {

}