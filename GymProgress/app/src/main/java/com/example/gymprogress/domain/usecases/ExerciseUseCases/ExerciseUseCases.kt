package com.example.gymprogress.domain.usecases.ExerciseUseCases

import com.example.gymprogress.data.repository.ExerciseRepository
import com.example.gymprogress.domain.usecases.LoadExercisesUseCase


class ExerciseUseCases(private val repository: ExerciseRepository) {

    val loadExercises = LoadExercisesUseCase(repository)

    val createExercise = CreateExerciseUseCase(repository)

    suspend fun deleteExerciseById(id: Int){
        repository.deleteExerciseById(id)
    }

    suspend fun deleteExercisesBySessionId(sessionId: Int){
        repository.deleteExerciseBySessionId(sessionId)
    }
}