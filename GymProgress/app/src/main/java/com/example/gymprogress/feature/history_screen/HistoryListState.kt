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
import com.example.gymprogress.ui.model.asSession
import com.example.gymprogress.ui.model.asSessionUi
import com.example.gymprogress.ui.util.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


//HistoryList that stores the sessions
data class HistoryListState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val sessions: List<SessionUi> = emptyList(),
    val createSession: SessionUi = SessionUi()
)


sealed class HistoryListStateEvent {
    data class CreateSession(val title: String, val latitude: Double, val longitude: Double): HistoryListStateEvent()
    object ChangeFinished: HistoryListStateEvent()
}

class HistoryViewModel(private val sessionOperations: SessionUseCases) : ViewModel(){
    private val _state = MutableStateFlow(HistoryListState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadSessions()
    }

    fun createSession(sessionUi: SessionUi){
        viewModelScope.launch {
            sessionOperations.creatSession(sessionUi.asSession())
        }
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

    fun onSave(name: String){
        viewModelScope.launch {
            try{
                _state.update { it.copy(
                    createSession = it.createSession.copy(name = name, latitude = 47.497913, longitude = 19.040236)
                ) }
                sessionOperations.creatSession(state.value.createSession.asSession())
            }
            catch (e: Exception){

            }
            loadSessions()
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
