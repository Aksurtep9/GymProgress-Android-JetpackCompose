package com.example.gymprogress.ui.model

import com.example.gymprogress.domain.model.Exercise
import com.example.gymprogress.domain.model.Session
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import java.time.LocalDateTime

data class ExerciseUi(
    val id: Int = 0,
    val name: String = "",
    val sessionId: Int,
    val maxWeight: Float = 0.0f
)

fun ExerciseUi.asExerciseUi(): ExerciseUi = ExerciseUi(
    id = id,
    name = name,
    sessionId = sessionId,
    maxWeight = maxWeight,
)

fun ExerciseUi.asExercise(): Exercise = Exercise(
    id = id,
    name = name,
    sessionId = sessionId,
    maxWeight = maxWeight
)