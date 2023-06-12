package com.example.gymprogress.data.repository

import com.example.gymprogress.data.entities.ExerciseTypeEntity
import kotlinx.coroutines.flow.Flow

interface ExerciseTypeRepository {
    fun getAllExerciseTypes(): Flow<List<ExerciseTypeEntity>>
    suspend fun addExerciseType(name: String)
}