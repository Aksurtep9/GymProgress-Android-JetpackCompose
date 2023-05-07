package com.example.gymprogress.data.repository

import com.example.gymprogress.data.entities.SetEntity
import kotlinx.coroutines.flow.Flow

interface SetRepository {
    suspend fun insertSet(set: SetEntity)

    fun getSetsForExercise(exerciseId: Int): Flow<List<SetEntity>>

    fun getSetById(id: Int): Flow<SetEntity>

    suspend fun deleteSet(id: Int)
}