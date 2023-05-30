package com.example.gymprogress.data.repository

import com.example.gymprogress.data.dao.ExerciseDao
import com.example.gymprogress.data.entities.ExerciseEntity
import com.example.gymprogress.domain.model.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ExerciseRepositoryImpl(private val exerciseDao: ExerciseDao) : ExerciseRepository {
    override suspend fun insertExercise(exercise: ExerciseEntity) {
        exerciseDao.insertExercise(exercise)
    }

    override fun getExercisesForSession(sessionId: Int): Flow<List<ExerciseEntity>> {
        return exerciseDao.getExercisesForSession(sessionId)
    }

    override fun getExerciseById(exerciseId: Int): Flow<ExerciseEntity> {
        return exerciseDao.getExerciseById(exerciseId)
    }

    override suspend fun deleteExerciseById(exerciseId: Int) {
        return exerciseDao.deleteExerciseById(exerciseId)
    }

    override suspend fun deleteExerciseBySessionId(sessionId: Int) {
        return exerciseDao.deleteExerciseBySessionId(sessionId)
    }
}