package com.example.gymprogress.domain.usecases

import com.example.gymprogress.data.repository.SessionRepository
import com.example.gymprogress.domain.model.Session
import com.example.gymprogress.domain.model.asSessionEntity

class SaveSessionUseCase(private val session_repository: SessionRepository) {

    suspend operator fun invoke(session: Session){
        session_repository.insertSession(session.asSessionEntity())
    }
}