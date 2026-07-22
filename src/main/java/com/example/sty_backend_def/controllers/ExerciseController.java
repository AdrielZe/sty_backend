package com.example.sty_backend_def.controllers;

import com.example.sty_backend_def.domains.models.exercise.dtos.ExerciseCatalogResponseDto;
import com.example.sty_backend_def.domains.models.exercise.dtos.ExerciseRequestDto;
import com.example.sty_backend_def.services.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService service) {
        this.exerciseService = service;
    }

    @GetMapping
    public ResponseEntity<List<ExerciseCatalogResponseDto>> getExercises() {
       return ResponseEntity.ok(exerciseService.getExercises());
    }

    @PostMapping
    ResponseEntity<List<ExerciseCatalogResponseDto>> createExercises(@RequestBody @Valid List<ExerciseRequestDto> data) {
        return ResponseEntity.ok(exerciseService.saveAll(data));
    }
}
