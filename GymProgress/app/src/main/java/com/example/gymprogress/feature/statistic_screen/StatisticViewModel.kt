package com.example.gymprogress.feature.statistic_screen

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gymprogress.GymApplication
import com.example.gymprogress.data.repository.ExerciseTypeRepository
import com.example.gymprogress.data.repository.SessionRepository
import com.example.gymprogress.domain.usecases.ExerciseTypeUseCases.ExerciseTypeUseCases
import com.example.gymprogress.domain.usecases.ExerciseUseCases.ExerciseUseCases
import com.example.gymprogress.domain.usecases.SessionUseCases
import com.example.gymprogress.ui.model.ExerciseTypeUi
import com.example.gymprogress.ui.model.asExerciseTypeUi
import com.example.gymprogress.ui.theme.GymProgressTheme
import com.himanshoe.charty.line.model.LineData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

sealed class StatisticState{
    object Loading : StatisticState()

    data class Error(val error: Throwable): StatisticState()

    data class Result(val statList: List<LineData>): StatisticState()
}


class StatisticViewModel(private val exerciseOperations: ExerciseUseCases, private val exerciseTypeOperations: ExerciseTypeUseCases, private val sessionOperations: SessionUseCases): ViewModel(){

    private val _state = MutableStateFlow<StatisticState>(StatisticState.Loading)
    val state = _state.asStateFlow()

    val exerciseTypesFlow = MutableStateFlow<List<ExerciseTypeUi>>(emptyList())


    init{
        loadExerciseTypes()
    }

    fun loadStatistic(exerciseTypeName: String){
        Log.d("Viewmodel Stat before scope", exerciseTypeName)
        viewModelScope.launch {
            try{
                _state.value = StatisticState.Loading
                    val exercises = exerciseOperations.getExercisesByName(exerciseTypeName).getOrThrow()
                    Log.d("Exercises size", exercises.size.toString())
                    val resultList :MutableList<LineData> = ArrayList()
                    exercises.forEach {exercise ->
                        val session = sessionOperations.loadSession.invoke(exercise.sessionId).getOrThrow()
                        resultList.add(LineData(session.date.toString(), exercise.maxWeight))
                        Log.d("VIEWMODEL STAT: ", exercise.name)
                    }
                    _state.value = StatisticState.Result(statList = resultList)

            }
            catch (e: Exception){
                Log.d("VIEWMODEL EXCEPTION", e.toString())

            }
        }
    }

    fun loadExerciseTypes(){
        viewModelScope.launch {
            try{
                val exercises = exerciseTypeOperations.loadExerciseTypes().getOrThrow()
                .map { it.asExerciseTypeUi()}
                exerciseTypesFlow.emit(exercises)
            }
            catch (e: Exception){
                exerciseTypesFlow.emit(emptyList())
            }
        }
    }


    fun chooseExerciseType(exerciseTypeName: String){
        Log.d("CHOOSE EXERCISE", exerciseTypeName)
        loadStatistic(exerciseTypeName = exerciseTypeName)
    }


    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val exerciseOperations = ExerciseUseCases(GymApplication.exerciseRepository)
                val exerciseTypeOperations = ExerciseTypeUseCases(GymApplication.exerciseTypeRepository)
                val sessionOperations = SessionUseCases(GymApplication.sessionRepository)
                StatisticViewModel(
                    exerciseOperations,
                    exerciseTypeOperations,
                    sessionOperations
                )
                }
            }
        }
    }
