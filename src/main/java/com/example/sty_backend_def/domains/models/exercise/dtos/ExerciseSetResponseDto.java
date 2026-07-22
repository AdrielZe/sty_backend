package com.example.sty_backend_def.domains.models.exercise.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ExerciseSetResponseDto (
        String setIndex,
        String reps,
        String weight,
        Boolean isCompleted,
        String previousReps,
        String previousWeight,
        String timeValue,
        String distanceValue,
        String previousTime,
        String previousDistance,
        String technique,
        String targetReps,
        String targetWeight
){}
