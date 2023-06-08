package com.example.gymprogress.data.repository

import com.example.gymprogress.data.entities.SessionEntity
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun insertSession(session: SessionEntity)

    fun getAllSessions(): Flow<List<SessionEntity>>

    fun getSessionById(id: Int): Flow<SessionEntity>

    fun deleteSessionById(id: Int)

    fun updateSessionFinished(finished: Boolean, id: Int)
}