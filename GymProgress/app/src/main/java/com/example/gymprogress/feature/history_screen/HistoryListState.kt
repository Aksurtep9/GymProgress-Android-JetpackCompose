package com.example.gymprogress.feature.history_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymprogress.GymApplication
import com.example.gymprogress.domain.usecases.SessionUseCases
import com.example.gymprogress.feature.session_screen.SessionState
import com.example.gymprogress.ui.model.SessionUi
import com.example.gymprogress.ui.model.asSessionUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


//HistoryList that stores the sessions
data class HistoryListState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val sessions: List<SessionUi> = emptyList()

)

class HistoryViewModel(private val sessionOperations: SessionUseCases) : ViewModel(){
    private val _state = MutableStateFlow(HistoryListState())
    val state = _state.asStateFlow()

    init {
        loadSessions()
    }

    private fun loadSessions() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
                    val sessions =
                        sessionOperations.loadSessions().getOrThrow().map { it.asSessionUi() }
                    _state.update { it.copy(isLoading = false, sessions = sessions) }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e
                    )
                }
            }
        }
    }
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val sessionOperations = SessionUseCases(GymApplication.sessionRepository)
                HistoryViewModel(
                    sessionOperations = sessionOperations
                )
            }
        }
    }
}
