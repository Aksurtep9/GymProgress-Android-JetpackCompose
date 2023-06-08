package com.example.gymprogress.domain.usecases

import com.example.gymprogress.data.repository.SessionRepository

class UpdateSessionFinishedUseCase(private val repository: SessionRepository) {

    suspend operator fun invoke(finished: Boolean, id: Int){
        repository.updateSessionFinished(finished,id)
    }
}