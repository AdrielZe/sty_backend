package com.example.sty_backend_def.services;

import com.example.sty_backend_def.domains.models.exercise.Exercise;
import com.example.sty_backend_def.domains.models.user.User;
import com.example.sty_backend_def.domains.models.workout.Workout;
import com.example.sty_backend_def.domains.models.workout.WorkoutRequestDto;
import com.example.sty_backend_def.domains.models.workout.WorkoutResponseDto;
import com.example.sty_backend_def.repositories.UserRepository;
import com.example.sty_backend_def.repositories.WorkoutRepository;
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

    public WorkoutResponseDto createWorkout(WorkoutRequestDto workout) {
        User userReference = userRepository.getReferenceById(workout.userId());

        Workout workoutToSave = workoutRepository.findById(workout.workoutId())
                .orElse(new Workout());

        workoutToSave.setId(workout.workoutId());
        workoutToSave.setName(workout.workoutName());
        workoutToSave.setDayOfWeek(workout.dayOfWeek());
        workoutToSave.setUser(userReference);

        List<Exercise> newExercises = workout.exercises();

        if (newExercises != null && !newExercises.isEmpty()) {
            newExercises.forEach(exercise -> exercise.setWorkout(workoutToSave));

            if (workoutToSave.getExerciseList() == null) {
                workoutToSave.setExerciseList(newExercises);
            } else {
                workoutToSave.getExerciseList().clear();
                workoutToSave.getExerciseList().addAll(newExercises);
            }
        } else {
            System.out.println("exercises list is null or empty");
        }

        workoutRepository.save(workoutToSave);

        return toWorkoutResponseDto(workoutToSave);
    }

    public List<WorkoutResponseDto> createWorkouts(List<WorkoutRequestDto> data) {
        List<Workout> workouts = data.stream().map(workout -> {
            User userReference = userRepository.getReferenceById(workout.userId());

            Workout workoutToSave = Workout.builder()
                    .id(workout.workoutId())
                    .user(userReference)
                    .name(workout.workoutName())
                    .dayOfWeek(workout.dayOfWeek())
                    .exerciseList(workout.exercises())
                    .build();

            List<Exercise> exercises = workoutToSave.getExerciseList();

            if (!exercises.isEmpty()) {
                exercises.forEach(exercise -> exercise.setWorkout(workoutToSave));
            } else {
                throw new RuntimeException("exercises are null");
            }

            workoutToSave.setExerciseList(exercises);
            return workoutToSave;
        }
        ).toList();

        workoutRepository.saveAll(workouts);
        return workouts.stream().map(this::toWorkoutResponseDto).toList();
    }

    public WorkoutResponseDto getWorkout(UUID workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout doesn't exist"));

        return toWorkoutResponseDto(workout);
    }

    public List<WorkoutResponseDto> getUserWorkouts(UUID userId) {
        List<Workout> workouts = workoutRepository.findAllByUserId(userId);

        if (workouts.isEmpty()) {
            throw new RuntimeException("User doesn't have any workouts");
        }

        return workouts.stream().map(this::toWorkoutResponseDto).toList();
    }

    public List<WorkoutResponseDto> getUserWorkoutsByDay(UUID userId, String dayOfWeek) {
        List<Workout> workouts = workoutRepository.findAllByUserIdAndDayOfWeek(userId, dayOfWeek);

        if (!workouts.isEmpty()) {
            return workouts.stream().map(this::toWorkoutResponseDto).toList();
        } else {
            throw new RuntimeException("User doesn't have any workout for this day");
        }
    }

    public void deleteWorkout(UUID id) {
        if (workoutRepository.existsById(id)) {
            workoutRepository.deleteById(id);
        } else {
            System.out.println("Workout not found. It was already deleted.");
        }
    }

    private WorkoutResponseDto toWorkoutResponseDto(Workout workout) {
        return WorkoutResponseDto.builder()
                .workoutName(workout.getName())
                .workoutId(workout.getId())
                .dayOfWeek(workout.getDayOfWeek())
                .exercises(workout.getExerciseList())
                .userId(workout.getUser().getId())
                .build();
    }

}
