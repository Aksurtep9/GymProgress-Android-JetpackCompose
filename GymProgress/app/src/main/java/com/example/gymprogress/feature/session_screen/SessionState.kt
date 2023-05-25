package com.example.gymprogress.feature.session_screen

import android.text.Editable.Factory
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymprogress.GymApplication
import com.example.gymprogress.domain.usecases.ExerciseUseCases.ExerciseUseCases
import com.example.gymprogress.feature.history_screen.HistoryListState
import com.example.gymprogress.ui.model.ExerciseUi
import com.example.gymprogress.ui.model.SetUi
import com.example.gymprogress.ui.model.asExercise
import com.example.gymprogress.ui.model.asExerciseUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class SessionState {
    object Loading : SessionState()
    data class Error(val error: Throwable) : SessionState()
    data class Result(val exerciseList : List<ExerciseUi>) : SessionState()

}

class SessionViewModel(private val exerciseOperations: ExerciseUseCases, private val savedStateHandle: SavedStateHandle) : ViewModel(){
    private val _state = MutableStateFlow<SessionState>(SessionState.Loading)
    val state = _state.asStateFlow()

    init {
        loadExercises()
    }

    private fun loadExercises(){
        val id = checkNotNull<Int>(savedStateHandle["sessionId"])
        viewModelScope.launch {
            try {
                CoroutineScope(coroutineContext).launch(Dispatchers.IO){

                    val exercises = exerciseOperations.loadExercises.loadExercises(id)
                        .getOrThrow().map {
                            it.asExerciseUi()
                        }
                    _state.value = SessionState.Result(exercises)
                }
            } catch (e: Exception){
                _state.value = SessionState.Error(e)
            }
        }
    }

    fun createExercise(name: String){
        var createdExercise = ExerciseUi()
        val id = checkNotNull<Int>(savedStateHandle["sessionId"])
        viewModelScope.launch {
            try{
                val _createEx = createdExercise.copy(name = name, sessionId = id)
                exerciseOperations.createExercise.invoke(_createEx.asExercise())
            }
            catch (e: Exception){

            }
        }
        loadExercises()

    }

    fun getSets(): List<SetUi> {
        return emptyList<SetUi>()
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val exerciseOperations = ExerciseUseCases(GymApplication.exerciseRepository)
                val savedStateHandle = createSavedStateHandle()
                SessionViewModel(
                    exerciseOperations = exerciseOperations,
                    savedStateHandle
                )
            }
        }
    }
}