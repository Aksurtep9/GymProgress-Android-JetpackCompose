package com.example.gymprogress.data.repository

import com.example.gymprogress.data.dao.SetDao
import com.example.gymprogress.data.entities.SetEntity
import kotlinx.coroutines.flow.Flow

class SetRepositoryImpl(private val setDao: SetDao): SetRepository {
    override suspend fun insertSet(set: SetEntity) {
        setDao.insertSet(set)
    }

    override fun getSetsForExercise(exerciseId: Int): Flow<List<SetEntity>> {
        return setDao.getSetsForExercise(exerciseId)
    }

    override fun getSetById(id: Int): Flow<SetEntity> {
        return setDao.getSetById(id)
    }

    override suspend fun deleteSet(id: Int) {
        setDao.deleteSet(id)
    }
}