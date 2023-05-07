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

    // Add other queries as needed
}