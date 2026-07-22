package com.example.sty_backend_def.services;

import com.example.sty_backend_def.domains.models.MuscleGroup;
import com.example.sty_backend_def.domains.models.exercise.Exercise;
import com.example.sty_backend_def.domains.models.exercise.ExerciseType;
import com.example.sty_backend_def.domains.models.exercise.dtos.ExerciseCatalogResponseDto;
import com.example.sty_backend_def.domains.models.exercise.dtos.ExerciseRequestDto;
import com.example.sty_backend_def.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository repository;

    public ExerciseService(ExerciseRepository repository) {
        this.repository = repository;
    }

    public List<ExerciseCatalogResponseDto> getExercises() {
        return repository.findAll().stream().map(
                exercise -> new ExerciseCatalogResponseDto(
                        exercise.getId(),
                        exercise.getName(),
                        exercise.getType(),
                        exercise.getMuscleGroup()
                )
        ).toList();
    }

    public List<ExerciseCatalogResponseDto> saveAll(List<ExerciseRequestDto> requestDtos) {
        List<Exercise> exerciseList = requestDtos.stream().map(exercise -> Exercise.builder()
                .id(exercise.id())
                .name(exercise.exerciseName())
                .type(ExerciseType.STRENGTH)
                .muscleGroup(MuscleGroup.CHEST)
                .build()
        ).toList();

        List<Exercise> response = repository.saveAll(exerciseList);

        return response.stream().map( exercise -> new ExerciseCatalogResponseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getType(),
                exercise.getMuscleGroup()
        )).toList();
    }
}
