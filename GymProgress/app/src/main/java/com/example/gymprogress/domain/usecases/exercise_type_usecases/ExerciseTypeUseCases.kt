package com.example.gymprogress.domain.usecases.exercise_type_usecases

import com.example.gymprogress.data.repository.ExerciseTypeRepository
import com.example.gymprogress.domain.model.ExerciseType
import com.example.gymprogress.domain.model.asExerciseType
import kotlinx.coroutines.flow.first
import java.io.IOException

class ExerciseTypeUseCases(private val repository: ExerciseTypeRepository) {

    suspend fun loadExerciseTypes(): Result<List<ExerciseType>>{
        return try{
            val exerciseTypes = repository.getAllExerciseTypes().first()
            Result.success(exerciseTypes.map { it.asExerciseType()})
        }
        catch (e: IOException) {
            Result.failure(e)
        }
    }

    suspend fun insertExerciseType(name: String){
        repository.addExerciseType(name)
    }
}