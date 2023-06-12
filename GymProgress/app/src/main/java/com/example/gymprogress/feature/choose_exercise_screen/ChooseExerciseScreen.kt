package com.example.gymprogress.feature.choose_exercise_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymprogress.feature.session_screen.SessionState
import com.example.gymprogress.ui.common.ExerciseTypeItem
import com.example.gymprogress.ui.common.SearchView
import com.example.gymprogress.ui.model.ExerciseTypeUi
import com.example.gymprogress.ui.model.toUiText
import java.util.*

@Composable
fun ChooseExerciseScreen(
        sessionId: Int,
        viewModel: ChooseExerciseViewModel = viewModel(factory = ChooseExerciseViewModel.Factory),
        onExerciseTypeClick: () -> Unit
){
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current
    val textState = remember { mutableStateOf(TextFieldValue(""))}

        when (state){
            is ChooseExerciseState.Loading -> CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondaryContainer
            )

            is ChooseExerciseState.Error -> Text(
                text = state.error.toUiText().asString(context)
            )

            is ChooseExerciseState.Result -> {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SearchView(state = textState)

                    var typeList = state.exerciseTypeList
                    val searchedText = textState.value.text

                    if(searchedText.isNotEmpty()){
                        var filteredExerciseList: MutableList<ExerciseTypeUi> = emptyList<ExerciseTypeUi>().toMutableList()
                        for(_type in typeList ){
                            if(_type.name.lowercase(Locale.getDefault())
                                    .contains(searchedText.lowercase(Locale.getDefault()))
                            ){
                                filteredExerciseList.add(_type)
                            }
                        }
                        typeList = filteredExerciseList
                    }
                    LazyColumn(modifier = Modifier.fillMaxWidth()){
                        items(typeList.size){
                            i ->
                            Log.d("MINE", sessionId.toString())
                            ExerciseTypeItem(exerciseTypeName = typeList[i].name, onItemClick = {
                                viewModel.createExercise(typeList[i].name, sessionId)
                                onExerciseTypeClick()
                            } )
                        }
                    }
                }

            }
        }
    }