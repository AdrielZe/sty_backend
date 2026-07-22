CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    profile_picture VARCHAR(255),
    name_display VARCHAR(255),
    weekly_goal INTEGER,
    weight_kg FLOAT,
    age_years INTEGER,
    gender VARCHAR(50),
    accent_theme_name VARCHAR(50)
);


CREATE TABLE workouts (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_completed BOOLEAN,
    is_ongoing BOOLEAN,
    is_paused BOOLEAN,
    is_deleted BOOLEAN,
    day_of_week VARCHAR(25),
    completion_date DATE,
    completion_time TIME,
    start_time BIGINT,
    accumulated_time BIGINT,
    estimated_time INTEGER,
    progress FLOAT,
    rescheduled_to_day_of_week VARCHAR(25),
    rescheduled_week_start VARCHAR(25),
    user_id UUID NOT NULL,
    CONSTRAINT fk_workouts_users FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE histories (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    completion_date DATE,
    completion_time TIME,
    difficulty VARCHAR(255),
    duration_millis BIGINT,
    user_id UUID NOT NULL,
    workout_id UUID,
    CONSTRAINT fk_histories_workouts FOREIGN KEY (workout_id) REFERENCES workouts(id),
    CONSTRAINT fk_histories_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE exercises (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    time_value VARCHAR(50),
    distance_value VARCHAR(50),
    muscle_group VARCHAR(50),
    is_default BOOLEAN,
    is_completed BOOLEAN,
    sets_completed INTEGER,
    weight_record INTEGER,
    workout_id UUID,
    history_id UUID,
    CONSTRAINT fk_exercises_workout FOREIGN KEY (workout_id) REFERENCES workouts(id),
    CONSTRAINT fk_exercises_histories FOREIGN KEY (history_id) REFERENCES histories(id)
);

CREATE TABLE exercise_sets (
    id UUID PRIMARY KEY,
    set_index INTEGER NOT NULL,
    reps VARCHAR(10),
    weight VARCHAR(10),
    is_completed BOOLEAN,
    previous_reps VARCHAR(10),
    previous_weight VARCHAR(10),
    time_value VARCHAR(10),
    distance_value VARCHAR(10),
    previous_time VARCHAR(10),
    previous_distance VARCHAR(10),
    technique VARCHAR(50),
    target_reps VARCHAR(10),
    target_weight VARCHAR(10),
    exercise_id UUID NOT NULL,
    CONSTRAINT fk_exercise_sets_exercises FOREIGN KEY (exercise_id) REFERENCES exercises(id)
);

CREATE TABLE records (
    user_id UUID PRIMARY KEY,
    strength_record REAL,
    CONSTRAINT fk_records_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE exercises_records (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    weight REAL,
    record_id UUID NOT NULL,
    CONSTRAINT fk_exercises_records_records FOREIGN KEY (record_id) REFERENCES records(user_id)
)