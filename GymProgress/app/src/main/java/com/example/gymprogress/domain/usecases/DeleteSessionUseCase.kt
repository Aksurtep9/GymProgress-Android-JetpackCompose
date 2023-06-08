package com.example.gymprogress.domain.usecases

import com.example.gymprogress.data.repository.SessionRepository
import com.example.gymprogress.domain.model.Session
import com.example.gymprogress.domain.model.asSession
import kotlinx.coroutines.flow.first
import java.io.IOException

class DeleteSessionUseCase(private val repository: SessionRepository) {

    suspend operator fun invoke(id: Int) {
        try {
            repository.deleteSessionById(id);
        } catch (e: IOException) {
        }
    }
}