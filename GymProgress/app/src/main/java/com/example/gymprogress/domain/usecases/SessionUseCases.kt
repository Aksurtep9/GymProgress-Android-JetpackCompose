package com.example.gymprogress.domain.usecases

import com.example.gymprogress.data.repository.SessionRepository

class SessionUseCases(repository: SessionRepository) {
    val loadSessions = LoadSessionsUseCase(repository)
    val creatSession = CreatSessionUseCase(repository)
    val loadSession = LoadSessionUseCase(repository)
    val deleteSession = DeleteSessionUseCase(repository)
    val updateSessionFinished = UpdateSessionFinishedUseCase(repository)
}