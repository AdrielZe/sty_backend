package com.example.sty_backend_def.domains.models.exercise;

import com.example.sty_backend_def.domains.models.*;
import com.example.sty_backend_def.domains.models.history.History;
import com.example.sty_backend_def.domains.models.workout.Workout;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExerciseType type;

    @Column(name = "time_value")
    private String timeValue;

    @Column(name = "distance_value")
    private String distanceValue;

    @Column(name = "muscle_group")
    @Enumerated(EnumType.STRING)
    private MuscleGroup muscleGroup;

    @Column(name = "is_default")
    private Boolean isDefault = false;

    @Column(name = "is_completed")
    private Boolean isCompleted = false;

    @Column(name = "sets_completed")
    private Integer setsCompleted;

    @Column(name = "weight_record")
    private Integer weightRecord;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSet> exerciseSetList;
}
