package com.example.sty_backend_def.services;

import com.example.sty_backend_def.domains.models.exercise.Exercise;
import com.example.sty_backend_def.domains.models.user.User;
import com.example.sty_backend_def.domains.models.workout.Workout;
import com.example.sty_backend_def.domains.models.workout.WorkoutRequestDto;
import com.example.sty_backend_def.domains.models.workout.WorkoutResponseDto;
import com.example.sty_backend_def.repositories.UserRepository;
import com.example.sty_backend_def.repositories.WorkoutRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {

    WorkoutRepository workoutRepository;
    UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    public String createWorkout(WorkoutRequestDto workout) {
        System.out.println("request: " + workout);
        User userReference = userRepository.getReferenceById(workout.userId());

        if (!workoutRepository.existsById(workout.workoutId())){
            Workout workoutToSave = Workout.builder()
                    .id(workout.workoutId())
                    .name(workout.workoutName())
                    .dayOfWeek(workout.dayOfWeek())
                    .user(userReference)
                    .build();

            List<Exercise> exercises = workout.exercises();

            if (exercises != null) {
                exercises.forEach(exercise -> {
                    exercise.setWorkout(workoutToSave);
                });
                workoutToSave.setExerciseList(exercises);
            } else {
                System.out.println("exercise is null");
            }

            exercises.forEach( exercise -> {
                System.out.println("EXERCISES: " + exercise);

            });
            workoutRepository.save(workoutToSave);
            return workout.workoutName();
        } else {
            throw new RuntimeException("Workout already saved");
        }
    }

    public WorkoutResponseDto getWorkout(UUID workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout doesn't exist"));

            return WorkoutResponseDto.builder()
                    .workoutId(workout.getId())
                    .userId(workout.getUser().getId())
                    .workoutName(workout.getName())
                    .dayOfWeek(workout.getDayOfWeek())
                    .exercises(workout.getExerciseList())
                    .build();
    }

    public List<WorkoutResponseDto> getUserWorkouts(UUID userId) {
        List<Workout> workouts = workoutRepository.findAllByUserId(userId);

        if (workouts.isEmpty()) {
            throw new RuntimeException("User doesn't have any workouts");
        }

        System.out.println(workouts);
        System.out.println(workouts.stream());

        return workouts.stream().map(workout -> new WorkoutResponseDto(
                workout.getId(),
                workout.getUser().getId(),
                workout.getName(),
                workout.getDayOfWeek(),
                workout.getExerciseList()
        )).toList();

    }

    public List<WorkoutResponseDto> getUserWorkoutsByDay(UUID userId, String dayOfWeek) {
        List<Workout> workouts = workoutRepository.findAllByUserIdAndDayOfWeek(userId, dayOfWeek);

        if (!workouts.isEmpty()) {
            return workouts.stream().map(workout ->
                    new WorkoutResponseDto(
                            workout.getId(),
                            workout.getUser().getId(),
                            workout.getName(),
                            workout.getDayOfWeek(),
                            workout.getExerciseList()
                    )

            ).toList();
        } else {
            throw new RuntimeException("User doesn't have any workout for this day");
        }

    }

}
