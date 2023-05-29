package com.example.gymprogress.domain.usecases.SetUseCases

import com.example.gymprogress.data.repository.SetRepository
import com.example.gymprogress.domain.model.Set
import com.example.gymprogress.domain.model.asExerciseType
import com.example.gymprogress.domain.model.asSet
import com.example.gymprogress.domain.model.asSetEntity
import kotlinx.coroutines.flow.first
import java.io.IOException

class SetUseCases(private val repository: SetRepository) {

    suspend fun loadSets(exerciseId: Int): Result<List<Set>>{
        return try{
            val sets = repository.getSetsForExercise(exerciseId).first()
            Result.success(sets.map { it.asSet()})
        }
        catch (e: IOException) {
            Result.failure(e)
        }
    }

    suspend fun insertSet(set: Set){
        repository.insertSet(set.asSetEntity())
    }

    suspend fun deleteSet(set: Set){
        repository.deleteSet(set.id)
    }
}