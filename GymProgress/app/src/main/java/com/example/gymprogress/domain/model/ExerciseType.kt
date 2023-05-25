package com.example.gymprogress.domain.model

import com.example.gymprogress.data.entities.ExerciseEntity
import com.example.gymprogress.data.entities.ExerciseTypeEntity

data class ExerciseType(
    val id: Int,
    val name: String
)

fun ExerciseTypeEntity.asExerciseType(): ExerciseType = ExerciseType(
    id = id,
    name = name
)

fun ExerciseType.asExerciseTypeEntity(): ExerciseTypeEntity= ExerciseTypeEntity(
    id = id,
    name = name
)
