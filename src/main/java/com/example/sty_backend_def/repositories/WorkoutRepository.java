package com.example.sty_backend_def.repositories;

import com.example.sty_backend_def.domains.models.workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
   List<Workout> findAllByUserId(UUID userId);
   List<Workout> findAllByUserIdAndDayOfWeek(UUID userId, String dayOfWeek);
}
