package com.example.gymprogress.domain.usecases.ExerciseUseCases

import android.util.Log
import com.example.gymprogress.data.repository.ExerciseRepository
import com.example.gymprogress.domain.model.Exercise
import com.example.gymprogress.domain.model.asExercise
import com.example.gymprogress.domain.usecases.LoadExercisesUseCase
import com.example.gymprogress.ui.model.ExerciseUi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import java.io.IOException


class ExerciseUseCases(private val repository: ExerciseRepository) {

    val loadExercises = LoadExercisesUseCase(repository)

    val createExercise = CreateExerciseUseCase(repository)

    suspend fun deleteExerciseById(id: Int){
        repository.deleteExerciseById(id)
    }

    suspend fun deleteExercisesBySessionId(sessionId: Int){
        repository.deleteExerciseBySessionId(sessionId)
    }

    suspend fun getExerciseById(id: Int): Result<Exercise> {
        return try{
            val exercise = repository.getExerciseById(id).first()
            Result.success(exercise.asExercise())
        }
        catch (e: IOException){
            Result.failure(e)
        }
    }

    suspend fun getExercisesByName(name: String): Result<List<Exercise>>{
        return try{
            val exercises = repository.getExercisesByName(name).first()
            Log.d("USECASE NOT FOR", exercises.toString())
            exercises.forEach{
                ex -> Log.d("USECASE", ex.exerciseId.toString())
            }
            Result.success(exercises.map { it.asExercise() })
        }
        catch (e: IOException){
            Result.failure(e)
        }
    }

    suspend fun updateMaxWeightForId(newMaxWeight: Float, id: Int){
            repository.updateMaxWeightforId(newMaxWeight,id)
    }

}