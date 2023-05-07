package com.example.gymprogress.feature.history_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.HistoryToggleOff
import androidx.compose.material.icons.filled.Start
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymprogress.R
import com.example.gymprogress.ui.model.toUiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onListItemClick: (Int) -> Unit,
    onFabClick: () -> Unit,
    viewModel: HistoryViewModel = viewModel(factory = HistoryViewModel.Factory),

    ){
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = onFabClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(
                    color = if (state is HistoryListState.Loading || state is HistoryListState.Error) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.background
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is HistoryListState.Loading -> CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
                is HistoryListState.Error -> Text(
                    text = state.error.toUiText().asString(context)
                )
                is HistoryListState.Result -> {
                    //state.sessionList
                    if (state.sessionList.isEmpty()) {
                        Text(text = stringResource(id = R.string.text_empty_todo_list))
                    } else {
                       Column{
                           LazyColumn(modifier = Modifier.fillMaxSize()){
                               items(state.sessionList.size) { i ->
                                   ListItem(
                                       headlineText = {
                                           Row(verticalAlignment = Alignment.CenterVertically,
                                               modifier = Modifier.fillMaxWidth(),
                                           horizontalArrangement = Arrangement.SpaceBetween) {
                                                   IconButton(onClick = { },
                                                            enabled = !state.sessionList[i].finished
                                                   ){
                                                       if(!state.sessionList[i].finished){
                                                           Icon(imageVector = Icons.Default.ArrowRight, contentDescription = null, modifier = Modifier.size(40.dp), tint = Color.Green)
                                                       }
                                                       else{
                                                           Icon(imageVector = Icons.Default.HistoryToggleOff, contentDescription = null, modifier = Modifier.size(40.dp))

                                                       }
                                                   }
                                               Text(text = state.sessionList[i].name,
                                               fontSize = 24.sp,
                                               modifier = Modifier.padding(20.dp))
                                               IconButton(
                                                   onClick = {},
                                               ){
                                                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                                               }
                                           }
                                       },
                                       modifier = Modifier.clickable(onClick = { onListItemClick(state.sessionList[i].id) })
                                   )
                                   if (i != state.sessionList.lastIndex) {
                                       Divider(
                                           thickness = 2.dp,
                                           color = MaterialTheme.colorScheme.secondaryContainer
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
}