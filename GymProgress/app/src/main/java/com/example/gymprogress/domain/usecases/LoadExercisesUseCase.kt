package com.example.gymprogress.domain.usecases

import com.example.gymprogress.data.repository.ExerciseRepository
import com.example.gymprogress.domain.model.Exercise
import com.example.gymprogress.domain.model.asExercise
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class LoadExercisesUseCase(private val exercise_repository: ExerciseRepository) {

    suspend fun loadExercises(sessionId: Int): Result<List<Exercise>>{
        return try{
            val exercises = exercise_repository.getExercisesForSession(sessionId).first()
            Result.success(exercises.map {it.asExercise()})
        }catch (e: IOException){
            Result.failure(e)
        }
    }
}