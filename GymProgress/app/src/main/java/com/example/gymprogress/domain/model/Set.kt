package com.example.gymprogress.domain.model

import com.example.gymprogress.data.entities.SetEntity

data class Set(
    val id: Int,
    val exerciseId: Int,
    val weight: Float,
    val reps: Int
)

fun SetEntity.asSet(): Set = Set(
    id = id,
    exerciseId = exerciseId,
    weight = weight,
    reps = reps
)

fun Set.asSetEntity(): SetEntity = SetEntity(
    id = id,
    exerciseId = exerciseId,
    weight = weight,
    reps = reps
)

