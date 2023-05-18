package com.example.gymprogress.domain.usecases

import com.example.gymprogress.data.repository.SessionRepository
import com.example.gymprogress.domain.model.Session
import com.example.gymprogress.domain.model.asSession
import kotlinx.coroutines.flow.first
import java.io.IOException

class LoadSessionsUseCase(private val session_repository: SessionRepository) {

    suspend operator fun invoke(): Result<List<Session>>{
        return try{
            val sessions = session_repository.getAllSessions().first()
            Result.success(sessions.map {it.asSession()})
        }catch (e: IOException){
            Result.failure(e)
        }
    }
}