package com.example.gymprogress.feature.session_screen

import android.content.IntentSender.OnFinished
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymprogress.R
import com.example.gymprogress.feature.history_screen.HistoryListState
import com.example.gymprogress.ui.common.ExerciseItem
import com.example.gymprogress.ui.model.toUiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreen(
    //sessionId: Int,
    onFinished: () -> Unit,
    onAddExercise: () -> Unit,
    onAddNewExerciseType: () -> Unit,
    onNavigateBack: () -> Unit,
    onListItemClick: (Int) -> Unit,
    viewModel: SessionViewModel = viewModel(factory = SessionViewModel.Factory),
){
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = "Your exercises", fontSize = 16.sp ,modifier = Modifier.size(60.dp))},
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
            }, actions = {
                TextButton(onClick = { onFinished }, modifier =  Modifier.size(40.dp) ){
                    Text(text = "Finish")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),horizontalArrangement = Arrangement.SpaceAround) {
                    TextButton(onClick = { onAddExercise }) {
                        Text(text = "Add exercise")
                    }
                    TextButton(onClick = { onAddNewExerciseType },modifier = Modifier.size(60.dp) ) {
                        Text(text = "Add new exercise type", fontSize = 10.sp)
                    }
                }
            }
            },
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(
                    color = if (state is SessionState.Loading || state is SessionState.Error) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.background
                    }
                ),
            contentAlignment = Alignment.Center
        ){
            when (state) {
                is SessionState.Loading -> CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondaryContainer
                )

                is SessionState.Error -> Text(
                    text = state.error.toUiText().asString(context)
                )

                is SessionState.Result -> {
                    //state.sessionList
                    if (state.exerciseList.isEmpty()) {
                        Text(text = stringResource(id = R.string.text_empty_exercise_list))
                    } else {
                        Column {
                            LazyColumn {
                                items(state.exerciseList.size) { i ->
                                    ExerciseItem(
                                        exercise = state.exerciseList[i],
                                        onAddSetClick = { /* TODO */ },
                                        onDelete = { },
                                        onExerciseClick = { onListItemClick(state.exerciseList[i].id) },
                                        onDeleteClick = { state.exerciseList[i].id },
                                        sets = viewModel.getSets()
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}