package com.example.gymprogress.data.repository

import com.example.gymprogress.data.dao.SessionDao
import com.example.gymprogress.data.entities.SessionEntity
import kotlinx.coroutines.flow.Flow

class SessionRepositoryImpl(private val sessionDao: SessionDao): SessionRepository {
    override suspend fun insertSession(session: SessionEntity) {
        sessionDao.insertSession(session)
    }

    override fun getAllSessions(): Flow<List<SessionEntity>> {
        return sessionDao.getAllSessions()
    }

    override fun getSessionById(id: Int): Flow<SessionEntity> {
        return sessionDao.getSessionById(id)
    }

}