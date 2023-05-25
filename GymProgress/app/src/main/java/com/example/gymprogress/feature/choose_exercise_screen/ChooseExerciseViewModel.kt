package com.example.gymprogress.feature.choose_exercise_screen

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymprogress.GymApplication
import com.example.gymprogress.domain.model.ExerciseType
import com.example.gymprogress.domain.usecases.ExerciseUseCases.ExerciseUseCases
import com.example.gymprogress.domain.usecases.exercise_type_usecases.ExerciseTypeUseCases
import com.example.gymprogress.feature.session_screen.SessionViewModel
import com.example.gymprogress.ui.model.ExerciseTypeUi
import com.example.gymprogress.ui.model.ExerciseUi
import com.example.gymprogress.ui.model.asExercise
import com.example.gymprogress.ui.model.asExerciseTypeUi
import kotlinx.coroutines.launch
import java.io.IOException


class ChooseExerciseViewModel(private val exerciseOperations: ExerciseUseCases, private val exerciseTypeOperations: ExerciseTypeUseCases, private val savedStateHandle: SavedStateHandle): ViewModel() {




    fun loadExerciseTypes(): List<ExerciseTypeUi>{
        lateinit var exerciseTypes: List<ExerciseTypeUi>
        viewModelScope.launch {
            try{
                exerciseTypes = exerciseTypeOperations.loadExerciseTypes()
                    .getOrThrow().map{
                        it.asExerciseTypeUi()
                    }
            }catch (e: IOException){
            }

        }
        return exerciseTypes
    }

    fun createExerciseType(name: String){
        viewModelScope.launch {
            try{
                exerciseTypeOperations.insertExerciseType(name)
            }
            catch (e: Exception){

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
    }
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val exerciseOperations = ExerciseUseCases(GymApplication.exerciseRepository)
                val exerciseTypeOperations = ExerciseTypeUseCases(GymApplication.exerciseTypeRepository)
                val savedStateHandle = createSavedStateHandle()
                SessionViewModel(
                    exerciseOperations = exerciseOperations,
                    savedStateHandle
                )
            }
        }
    }
}