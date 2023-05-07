package com.example.gymprogress.feature.session_screen

import android.text.Editable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymprogress.feature.history_screen.HistoryListState
import com.example.gymprogress.ui.model.ExerciseUi
import com.example.gymprogress.ui.model.SetUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SessionState {
    object Loading : SessionState()
    data class Error(val error: Throwable) : SessionState()
    data class Result(val exerciseList : List<ExerciseUi>) : SessionState()
}

class SessionViewModel() : ViewModel(){
    private val _state = MutableStateFlow<SessionState>(SessionState.Loading)
    val state = _state.asStateFlow()

    init {
        loadExercises()
    }

    private fun loadExercises(){
        viewModelScope.launch {
            try {
                _state.value = SessionState.Loading

                _state.value = SessionState.Result(
                    exerciseList = emptyList<ExerciseUi>()
                )
            } catch (e: Exception){
                _state.value = SessionState.Error(e)
            }
        }
    }

    public fun getSets(): List<SetUi> {
        return emptyList<SetUi>()
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer { SessionViewModel() }
        }
    }
}