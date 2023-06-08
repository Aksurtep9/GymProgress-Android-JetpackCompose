package com.example.gymprogress.feature.session_screen

import android.content.IntentSender.OnFinished
import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymprogress.R
import com.example.gymprogress.feature.history_screen.HistoryListState
import com.example.gymprogress.ui.common.AddNewExerciseTypeDialog
import com.example.gymprogress.ui.common.ExerciseItem
import com.example.gymprogress.ui.common.SetAdderDialog
import com.example.gymprogress.ui.model.ExerciseUi
import com.example.gymprogress.ui.model.toUiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreen(
    sessionId: Int,
    onFinished: () -> Unit,
    onAddExercise: () -> Unit,
    onAddNewExerciseType: () -> Unit,
    onNavigateBack: () -> Unit,
    onListItemClick: (Int) -> Unit,
    viewModel: SessionViewModel = viewModel(factory = SessionViewModel.Factory),
){
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    var pickedExercise = ExerciseUi()

    var showSetAdderDialog by remember { mutableStateOf(false)}
    var showExerciseAdderDialog by remember { mutableStateOf(false)}

    val setsFlow = viewModel.setsFlow.collectAsStateWithLifecycle().value



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.your_exercises), fontSize = 16.sp ,modifier = Modifier.size(60.dp))},
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
            }, actions = {
                TextButton(onClick = {
                    viewModel.updateSessionFinished()
                    onFinished() },

                    modifier =  Modifier.size(100.dp) ){
                    Text(text = stringResource(id = R.string.finished), fontSize = 18.sp)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),horizontalArrangement = Arrangement.SpaceBetween) {
                    TextButton(onClick = { onAddExercise() },enabled = !viewModel.finished, modifier = Modifier.size(120.dp)) {
                        Text(text = stringResource(id = R.string.add_new_exercise),fontSize = 16.sp)
                    }
                    TextButton(onClick = { showExerciseAdderDialog = true },enabled = !viewModel.finished,modifier = Modifier.size(120.dp) ) {
                        Text(text = stringResource(id = R.string.add_new_exercisetype), fontSize = 16.sp, textAlign = TextAlign.Center)
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
            contentAlignment = Alignment.TopCenter
        ){
            when (state) {
                is SessionState.Loading -> CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondaryContainer
                )

                is SessionState.Error -> Text(
                    text = state.error.toUiText().asString(context)
                )

                is SessionState.Result -> {

                    if (state.exerciseList.isEmpty()) {
                        Text(text = stringResource(id = R.string.text_empty_exercise_list))
                    } else {
                        Column {
                            LazyColumn {
                                items(state.exerciseList.size) { i ->

                                    val exercise = state.exerciseList[i]

                                    ExerciseItem(
                                        exercise = exercise,
                                        onAddSetClick = { showSetAdderDialog = true
                                                        pickedExercise = exercise},
                                        onDelete = { viewModel.deleteExercise(exercise)},
                                        onExerciseClick = {
                                            onListItemClick(exercise.id)
                                            setsFlow[exercise.id] ?: emptyList()
                                            },
                                        onDeleteSetClick = { viewModel.deleteSet(it) },
                                        sets = setsFlow[exercise.id] ?: emptyList()
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
        if(showExerciseAdderDialog){
            AddNewExerciseTypeDialog(
                onConfirm = {typeName -> viewModel.createExerciseType(typeName)
                            showExerciseAdderDialog = false},
                onDismiss = {showExerciseAdderDialog = false}

            )
        }
        if(showSetAdderDialog){
            SetAdderDialog(exercise = pickedExercise, onConfirm ={
                set -> viewModel.addSet(set)
                viewModel.getSets(exercise = pickedExercise)
                showSetAdderDialog = false
            }, onDismiss = {showSetAdderDialog = false}
            )
        }
    }
}