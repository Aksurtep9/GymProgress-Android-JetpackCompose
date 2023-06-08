package com.example.gymprogress.data.repository

import com.example.gymprogress.data.entities.ExerciseEntity
import com.example.gymprogress.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    suspend fun insertExercise(exercise: ExerciseEntity)
    fun getExercisesForSession(sessionId: Int): Flow<List<ExerciseEntity>>

    fun getExerciseById(exerciseId: Int): Flow<ExerciseEntity>

    fun getExercisesByName(name: String): Flow<List<ExerciseEntity>>

    suspend fun deleteExerciseById(exerciseId: Int)

    suspend fun deleteExerciseBySessionId(sessionId: Int)

    suspend fun updateMaxWeightforId(newMaxWeight: Float, exerciseId: Int)


}