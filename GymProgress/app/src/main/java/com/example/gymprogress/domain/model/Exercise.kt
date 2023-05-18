package com.example.gymprogress.domain.model

import com.example.gymprogress.data.entities.ExerciseEntity

data class Exercise(
    val id: Int,
    val name: String,
    val sessionId: Int,
    val maxWeight: Float = 0.0f
)

fun ExerciseEntity.asExercise(): Exercise = Exercise(
    id = exerciseId,
    name = name,
    sessionId = sessionId,
    maxWeight = maxWeight
)

fun Exercise.asExerciseEntity(): ExerciseEntity = ExerciseEntity(
    exerciseId = id,
    name = name,
    sessionId = sessionId,
    maxWeight = maxWeight
)