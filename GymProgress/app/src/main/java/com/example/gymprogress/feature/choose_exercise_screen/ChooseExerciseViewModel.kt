package com.example.gymprogress.feature.choose_exercise_screen

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymprogress.GymApplication
import com.example.gymprogress.domain.usecases.ExerciseUseCases.ExerciseUseCases
import com.example.gymprogress.domain.usecases.ExerciseTypeUseCases.ExerciseTypeUseCases
import com.example.gymprogress.ui.model.ExerciseTypeUi
import com.example.gymprogress.ui.model.ExerciseUi
import com.example.gymprogress.ui.model.asExercise
import com.example.gymprogress.ui.model.asExerciseTypeUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException


sealed class ChooseExerciseState {
    object Loading : ChooseExerciseState()

    data class Error(val error: Throwable): ChooseExerciseState()

    data class Result(val exerciseTypeList: List<ExerciseTypeUi>): ChooseExerciseState()
}

class ChooseExerciseViewModel(private val exerciseOperations: ExerciseUseCases, private val exerciseTypeOperations: ExerciseTypeUseCases, private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val _state = MutableStateFlow<ChooseExerciseState>(ChooseExerciseState.Loading)
    val state = _state.asStateFlow()
    init{
        loadExerciseTypes()
    }

    fun loadExerciseTypes() {
        viewModelScope.launch {
            try{
                CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
                    val exerciseTypes = exerciseTypeOperations.loadExerciseTypes()
                        .getOrThrow().map {
                            it.asExerciseTypeUi()
                        }
                    _state.value = ChooseExerciseState.Result(exerciseTypes)
                }
            }catch (e: IOException){
                _state.value = ChooseExerciseState.Error(e)
            }
        }
    }
    fun createExerciseType(name: String){
        viewModelScope.launch {
            try{
                exerciseTypeOperations.insertExerciseType(name)
            }
            catch (e: Exception){
            }
        }
        loadExerciseTypes()
    }


    fun createExercise(name: String, sessionId: Int){
        var createdExercise = ExerciseUi()
        viewModelScope.launch {
            try{
            CoroutineScope(coroutineContext).launch(Dispatchers.IO) {
                val createEx = createdExercise.copy(name = name, sessionId = sessionId)
                exerciseOperations.createExercise.invoke(createEx.asExercise())
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
                val savedStateHandle = createSavedStateHandle()
                ChooseExerciseViewModel(
                    exerciseOperations = exerciseOperations,
                    exerciseTypeOperations = exerciseTypeOperations,
                    savedStateHandle
                )
            }
        }
    }
}