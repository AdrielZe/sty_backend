package com.example.sty_backend_def.domains.models.workout;


import com.example.sty_backend_def.domains.models.exercise.Exercise;
import com.example.sty_backend_def.domains.models.history.History;
import com.example.sty_backend_def.domains.models.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workout {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_completed")
    private Boolean isCompleted = false;

    @Column(name = "is_ongoing")
    private Boolean isOngoing = false;

    @Column(name = "is_paused")
    private Boolean isPaused;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Column(name = "completion_time")
    private LocalTime completionTime;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "accumulatedTime")
    private Long accumulatedTime;

    @Column(name = "estimated_time")
    private Integer estimatedTime;

    @Column(name = "progress")
    private Float progress;

    @Column(name = "rescheduled_to_day_of_week")
    private String rescheduledToDayOfWeek;

    @Column(name = "rescheduled_week_start")
    private String rescheduledWeekStart;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> exerciseList = new ArrayList<>();

    @OneToMany(mappedBy = "workout")
    private List<History> historyList;
}
