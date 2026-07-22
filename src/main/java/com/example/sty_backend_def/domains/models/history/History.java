package com.example.sty_backend_def.domains.models.history;

import com.example.sty_backend_def.domains.models.user.User;
import com.example.sty_backend_def.domains.models.workout.Workout;
import com.example.sty_backend_def.domains.models.exercise.Exercise;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Column(name = "completion_time")
    private LocalTime completionTime;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "duration_millis")
    private Long durationMillis;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @OneToMany(mappedBy = "history")
    private List<Exercise> exerciseList = new ArrayList<>();
}
