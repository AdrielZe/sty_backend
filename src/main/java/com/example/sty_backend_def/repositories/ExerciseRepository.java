package com.example.sty_backend_def.repositories;

import com.example.sty_backend_def.domains.models.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, String> {
}
