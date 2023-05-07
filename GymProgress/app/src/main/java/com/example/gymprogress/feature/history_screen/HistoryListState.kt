package com.example.gymprogress.feature.history_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymprogress.ui.model.SessionUi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


//HistoryList that stores the sessions
sealed class HistoryListState {
    object Loading : HistoryListState()
    data class Error(val error: Throwable) : HistoryListState()
    data class Result(val sessionList : List<SessionUi>) : HistoryListState()
}

class HistoryViewModel() : ViewModel(){
    private val _state = MutableStateFlow<HistoryListState>(HistoryListState.Loading)
    val state = _state.asStateFlow()

    init {
        loadSessions()
    }

    private fun loadSessions() {
        viewModelScope.launch {
            try {
                _state.value = HistoryListState.Loading
                delay(2000)
                _state.value = HistoryListState.Result(

                    sessionList = listOf(
                        SessionUi(
                            id = 1,
                            name = "Hat + mell",
                            finished = true
                        ),
                        SessionUi(
                            id = 2,
                            name = "Has es kar"
                        ),
                    ),


                //sessionList = emptyList()
                )
            } catch (e: Exception){
                _state.value = HistoryListState.Error(e)
            }
        }
    }
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HistoryViewModel()
            }
        }
    }
}
