package com.example.gymprogress.domain.usecases

import com.example.gymprogress.data.repository.SessionRepository
import com.example.gymprogress.domain.model.Session
import com.example.gymprogress.domain.model.asSession
import kotlinx.coroutines.flow.first
import java.io.IOException

class LoadSessionUseCase(private val repository: SessionRepository) {

    suspend operator fun invoke(id: Int): Result<Session> {
        return try {
            Result.success(repository.getSessionById(id).first().asSession())
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

}