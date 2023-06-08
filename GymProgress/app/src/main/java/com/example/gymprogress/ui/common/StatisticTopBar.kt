package com.example.gymprogress.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymprogress.R
import com.example.gymprogress.ui.model.ExerciseTypeUi
import com.example.gymprogress.ui.model.ExerciseUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticTopBar(
    items: List<ExerciseTypeUi>,
    onNavigateBack: () -> Unit,
    onSelected : (ExerciseTypeUi) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedExercise by remember { mutableStateOf(ExerciseTypeUi())}

    TopAppBar(
        title = { Text(text = stringResource(id = R.string.statistics)) },
        navigationIcon = {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ){
                TextField(
                    value = selectedExercise.name,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().size(160.dp, 60.dp).align(Alignment.CenterVertically)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                )
                {
                    items.forEach{item ->
                        DropdownMenuItem(text = { Text(text = item.name) },
                            onClick = { selectedExercise = item
                                onSelected(selectedExercise)
                                expanded = false
                        })
                }
            }
            }
        }
    )
}

@Preview
@Composable
fun StatisticTopBarPreview() {
    val items = listOf(
        ExerciseTypeUi(1, "Test1"),
        ExerciseTypeUi(2, "Test2"),
    )
    StatisticTopBar(items = items, onSelected = { /* Handle exercise selection */ }, onNavigateBack = {})
}