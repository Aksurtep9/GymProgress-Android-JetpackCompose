package com.example.gymprogress.domain.usecases.ExerciseUseCases

import com.example.gymprogress.data.repository.ExerciseRepository
import com.example.gymprogress.domain.usecases.LoadExercisesUseCase


class ExerciseUseCases(repository: ExerciseRepository) {

    val loadExercises = LoadExercisesUseCase(repository)

    val createExercise = CreateExerciseUseCase(repository)

}