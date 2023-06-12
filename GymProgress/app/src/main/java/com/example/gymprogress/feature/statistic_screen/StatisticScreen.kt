package com.example.gymprogress.feature.statistic_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymprogress.feature.session_screen.SessionState
import com.example.gymprogress.ui.common.StatisticTopBar
import com.example.gymprogress.ui.model.toUiText
import com.himanshoe.charty.line.LineChart
import com.himanshoe.charty.line.model.LineData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticScreen(
    onNavigateBack: () -> Unit,
    viewModel: StatisticViewModel = viewModel(factory = StatisticViewModel.Factory)
){
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val exercises = viewModel.exerciseTypesFlow.collectAsStateWithLifecycle().value

    val context = LocalContext.current


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { StatisticTopBar(
            items = exercises,
            onSelected = { exerciseType ->
                if (exercises.isNotEmpty()) {
                    viewModel.chooseExerciseType(exerciseType.name)
                }
            },
            onNavigateBack = {onNavigateBack()}

        ) },

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when(state) {
                is StatisticState.Loading -> CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondaryContainer
                )

                is StatisticState.Error -> Text(text = state.error.toUiText().asString(context))

                is StatisticState.Result ->
                    if(state.statList.isEmpty()){

                    }
                    else {
                        LineChart(
                            modifier = Modifier.size(300.dp, 300.dp),
                            lineData = state.statList,
                            color = Color.Blue
                        )
                    }
            }
        }
    }
}

@Preview
@Composable
fun StatisticScreenPreview() {
    StatisticScreen(onNavigateBack = {})
}

