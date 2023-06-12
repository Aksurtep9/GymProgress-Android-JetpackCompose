package com.example.gymprogress.ui.model

import com.example.gymprogress.domain.model.Exercise
import com.example.gymprogress.domain.model.ExerciseType

data class ExerciseTypeUi(
    val id: Int = 0,
    val name: String = "",
)

fun ExerciseType.asExerciseTypeUi(): ExerciseTypeUi = ExerciseTypeUi(
    id = id,
    name = name,
)

fun ExerciseTypeUi.asTypeExercise(): ExerciseType = ExerciseType(
    id = id,
    name = name,
)