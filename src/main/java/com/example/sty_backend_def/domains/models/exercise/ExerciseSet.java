package com.example.sty_backend_def.domains.models.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "exercise_sets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSet {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "set_index", nullable = false)
    private Integer setIndex;

    @Column(name = "reps")
    private String reps;

    @Column(name = "weight")
    private String weight;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "previous_reps")
    private String previousReps;

    @Column(name = "previous_weight")
    private String previousWeight;

    @Column(name = "time_value")
    private String timeValue;

    @Column(name = "distance_value")
    private String distanceValue;

    @Column(name = "previous_time")
    private String previousTime;

    @Column(name = "previous_distance")
    private String previousDistance;

    @Column(name = "technique")
    private String technique;

    @Column(name = "target_reps")
    private String targetReps;

    @Column(name = "target_weight")
    private String targetWeight;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

}
