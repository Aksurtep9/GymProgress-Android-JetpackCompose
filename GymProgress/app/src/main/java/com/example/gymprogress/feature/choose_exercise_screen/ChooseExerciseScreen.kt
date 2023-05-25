package com.example.gymprogress.feature.choose_exercise_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymprogress.ui.common.ExerciseTypeItem
import com.example.gymprogress.ui.common.SearchView
import com.example.gymprogress.ui.model.ExerciseTypeUi
import java.util.*

@Composable
fun ChooseExerciseScreen(
        viewModel: ChooseExerciseViewModel = viewModel(factory = ChooseExerciseViewModel.Factory)
){
    val textState = remember { mutableStateOf(TextFieldValue(""))}
    Column(modifier = Modifier.fillMaxWidth()) {
        SearchView(state = textState)
        val resultList = viewModel.loadExerciseTypes().toMutableList()
        var filteredExerciseTypes: List<ExerciseTypeUi> = viewModel.loadExerciseTypes()
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            val searchedText = textState.value.text
            if(searchedText.isEmpty()){
                filteredExerciseTypes = viewModel.loadExerciseTypes()
            }
            else{
                for(type in filteredExerciseTypes){
                    if(type.name.lowercase(Locale.getDefault())
                            .contains(searchedText.lowercase(Locale.getDefault()))
                    ){
                        resultList.add(type)
                    }

                }
                filteredExerciseTypes = resultList
            }
            items(filteredExerciseTypes.size){
                i ->
                ExerciseTypeItem(exerciseTypeName = filteredExerciseTypes[i].name, onItemClick = {} )
            }
        }
    }
}
