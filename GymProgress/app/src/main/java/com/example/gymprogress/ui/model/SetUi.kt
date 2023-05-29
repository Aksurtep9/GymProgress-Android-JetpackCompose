package com.example.gymprogress.ui.model

import kotlinx.datetime.LocalDate
import java.time.LocalDateTime
import com.example.gymprogress.domain.model.Set

data class SetUi(
    val id: Int = 0,
    val exerciseId: Int,
    val weight: Float,
    val reps: Int
)

fun Set.asSetUi(): SetUi = SetUi(
    id = id,
    exerciseId = exerciseId,
    weight = weight,
    reps = reps
)

fun SetUi.asSet(): Set = Set(
    id = id,
    exerciseId = exerciseId,
    weight = weight,
    reps = reps
)

