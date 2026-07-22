package com.example.sty_backend_def.controllers;

import com.example.sty_backend_def.domains.models.workout.WorkoutRequestDto;
import com.example.sty_backend_def.services.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity createWorkout(@Valid @RequestBody WorkoutRequestDto data) {
        return ResponseEntity.ok(service.createWorkout(data));
    }


    // get single workout by id
    @GetMapping("/{id}")
    ResponseEntity getWorkout(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getWorkout(id));
    }

    // get all workouts from an user
    @GetMapping("/user/{userId}")
    ResponseEntity getUserWorkouts(@Valid @PathVariable UUID userId) {
        return ResponseEntity.ok(service.getUserWorkouts(userId));
    }


    // get all today's workouts that belongs to this user
    @GetMapping("/user/{userId}/{dayOfWeek}")
    ResponseEntity getUserWorkoutsByDay(@Valid @PathVariable UUID userId, @PathVariable String dayOfWeek) {
        return ResponseEntity.ok(service.getUserWorkoutsByDay(userId, dayOfWeek));
    }

    // get total workouts that are completed by this user

    // get weekly goal set for this user

    // get freestyle workout (if active)

}
