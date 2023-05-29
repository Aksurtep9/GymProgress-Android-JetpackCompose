package com.example.gymprogress.feature.session_screen

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.*
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymprogress.GymApplication
import com.example.gymprogress.domain.usecases.ExerciseUseCases.ExerciseUseCases
import com.example.gymprogress.domain.usecases.ExerciseTypeUseCases.ExerciseTypeUseCases
import com.example.gymprogress.domain.usecases.SetUseCases.SetUseCases
import com.example.gymprogress.ui.model.ExerciseUi
import com.example.gymprogress.ui.model.SetUi
import com.example.gymprogress.ui.model.asExercise
import com.example.gymprogress.ui.model.asExerciseUi
import com.example.gymprogress.ui.model.asSet
import com.example.gymprogress.ui.model.asSetUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SessionState {
    object Loading : SessionState()
    data class Error(val error: Throwable) : SessionState()
    data class Result(val exerciseList : List<ExerciseUi>) : SessionState()

}

class SessionViewModel(private val exerciseOperations: ExerciseUseCases,
                       private val exerciseTypeOperations: ExerciseTypeUseCases,
                       private val setOperations: SetUseCases,
                       private val savedStateHandle: SavedStateHandle) : ViewModel(){
    private val _state = MutableStateFlow<SessionState>(SessionState.Loading)
    val state = _state.asStateFlow()

    val setsFlow = MutableStateFlow<List<SetUi>>(emptyList())

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
                CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
                    val _createEx = createdExercise.copy(name = name, sessionId = id)
                    exerciseOperations.createExercise.invoke(_createEx.asExercise())
                }
            }
            catch (e: Exception){

            }
        }
        loadExercises()
    }

    fun createExerciseType(name: String){
        viewModelScope.launch {
            try{
                CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
                    exerciseTypeOperations.insertExerciseType(name)
                }
            }
            catch (e: Exception){

            }
        }
    }

    fun addSet(set: SetUi){
        viewModelScope.launch {
            try{
                CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
                    setOperations.insertSet(set.asSet())
                }
            }
            catch (e: Exception){

            }
        }
    }

    fun getSets(exercise: ExerciseUi) {

        var resultList = emptyList<SetUi>()
        viewModelScope.launch {
            try{
                CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
                    resultList = setOperations.loadSets(exercise.id).getOrThrow()
                        .map{
                            it.asSetUi()
                        }
                    setsFlow.emit(resultList)
                    Log.d("ViewModel SET", resultList[0].id.toString())
                }
            }
            catch (e: Exception){

            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val exerciseOperations = ExerciseUseCases(GymApplication.exerciseRepository)
                val exerciseTypeOperations = ExerciseTypeUseCases(GymApplication.exerciseTypeRepository)
                val setOperations = SetUseCases(GymApplication.setRepository)
                val savedStateHandle = createSavedStateHandle()
                SessionViewModel(
                    exerciseOperations = exerciseOperations,
                    exerciseTypeOperations = exerciseTypeOperations,
                    setOperations = setOperations,
                    savedStateHandle
                )
            }
        }
    }
}