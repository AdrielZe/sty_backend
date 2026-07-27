package com.example.sty_backend_def.controllers;

import com.example.sty_backend_def.domains.models.workout.WorkoutRequestDto;
import com.example.sty_backend_def.services.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    WorkoutService service;

    public WorkoutController(WorkoutService service) {
        this.service = service;
    }

    //EndPoints:

    // post workout
    // post weekly goal
    @PostMapping()
    public ResponseEntity createWorkout(@Valid @RequestBody WorkoutRequestDto data) {
        return ResponseEntity.ok(service.createWorkout(data));
    }

    @PostMapping("/sync")
    public ResponseEntity createWorkouts(@Valid @RequestBody List<WorkoutRequestDto> data) {
        return ResponseEntity.ok(service.createWorkouts(data));
    }

    // get single workout by id
    @GetMapping("/{id}")
    public ResponseEntity getWorkout(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getWorkout(id));
    }

    // get all workouts from an user
    @GetMapping("/user/{userId}")
    public ResponseEntity getUserWorkouts(@Valid @PathVariable UUID userId) {
        return ResponseEntity.ok(service.getUserWorkouts(userId));
    }


    // get all today's workouts that belongs to this user
    @GetMapping("/user/{userId}/{dayOfWeek}")
    public ResponseEntity getUserWorkoutsByDay(@Valid @PathVariable UUID userId, @PathVariable String dayOfWeek) {
        return ResponseEntity.ok(service.getUserWorkoutsByDay(userId, dayOfWeek));
    }

    // delete workout
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWorkout(@Valid @PathVariable UUID id) {
        service.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }

    //update workout

    // get total workouts that are completed by this user

    // get weekly goal set for this user

    // get freestyle workout (if active)

}
