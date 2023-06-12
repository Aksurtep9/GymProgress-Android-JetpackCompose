package com.example.gymprogress.domain.usecases

import com.example.gymprogress.data.repository.SessionRepository
import com.example.gymprogress.domain.model.Session
import com.example.gymprogress.domain.model.asSessionEntity

class CreatSessionUseCase(private val repository: SessionRepository) {

    suspend operator fun invoke(session: Session){
        repository.insertSession(session.asSessionEntity())
    }
}