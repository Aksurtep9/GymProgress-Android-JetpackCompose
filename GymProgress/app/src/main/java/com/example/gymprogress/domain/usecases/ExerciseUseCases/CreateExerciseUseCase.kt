package com.example.gymprogress.domain.usecases.ExerciseUseCases

import com.example.gymprogress.data.repository.ExerciseRepository
import com.example.gymprogress.domain.model.Exercise
import com.example.gymprogress.domain.model.asExerciseEntity
import com.example.gymprogress.domain.model.asSessionEntity

class CreateExerciseUseCase(private val repository: ExerciseRepository) {

    suspend operator fun invoke(exercise: Exercise){
        repository.insertExercise(exercise.asExerciseEntity())
    }
}