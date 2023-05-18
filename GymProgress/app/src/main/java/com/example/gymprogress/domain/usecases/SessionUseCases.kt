package com.example.gymprogress.domain.usecases

import com.example.gymprogress.data.repository.SessionRepository

class SessionUseCases(repository: SessionRepository) {
    val loadSessions = LoadSessionsUseCase(repository)
}