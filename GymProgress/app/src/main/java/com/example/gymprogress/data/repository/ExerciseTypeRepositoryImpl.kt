package com.example.gymprogress.data.repository

import com.example.gymprogress.data.dao.ExerciseTypeDao
import com.example.gymprogress.data.entities.ExerciseTypeEntity
import kotlinx.coroutines.flow.Flow

class ExerciseTypeRepositoryImpl(private val dao: ExerciseTypeDao) : ExerciseTypeRepository {
    override fun getAllExerciseTypes(): Flow<List<ExerciseTypeEntity>> {
        return dao.getAll()
    }

    override suspend fun addExerciseType(name: String) {
        dao.insert(ExerciseTypeEntity(name = name))
    }
}