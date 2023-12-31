package com.example.gymprogress.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymprogress.data.entities.SessionEntity
import com.example.gymprogress.domain.model.Session
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity)

    @Query("SELECT * FROM sessions")
    fun getAllSessions(): Flow<List<SessionEntity>>

    @Query("SELECT * FROM sessions WHERE id = :sessionId")
    fun getSessionById(sessionId: Int): Flow<SessionEntity>

    @Query("DELETE FROM sessions WHERE id = :sessionId")
    fun deleteSessionById(sessionId: Int)

    @Query("UPDATE sessions SET finished = :finished WHERE id = :id")
    fun updateSessionFinished(finished: Boolean, id: Int)
}