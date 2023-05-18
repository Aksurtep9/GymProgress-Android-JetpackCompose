package com.example.gymprogress.ui.common
/*

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExerciseDialog(
    exerciseList: List<String>,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var selectedExercise by remember { mutableStateOf(exerciseList[0]) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Select an exercise") },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { onDismiss() },
                    ) {
                        Text(text = "Dismiss")
                    }
                    Button(
                        onClick = { onSave(selectedExercise) },
                    ) {
                        Text(text = "Save")
                    }
                }
            },
            text = {
                Column {
                    DropdownMenu(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        expanded = false,
                        onDismissRequest = { },
                        toggle = {
                            Text(
                                text = selectedExercise,
                                modifier = Modifier.clickable(
                                    onClick = { }
                                )
                            )
                        },
                        dropdownOffset = PositioningConstants.Positioner(0.dp, 0.dp),
                        content = {
                            exerciseList.forEach { exercise ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedExercise = exercise
                                    }
                                ) {
                                    Text(text = exercise)
                                }
                            }
                        }
                    )
                }
            }
        )
    }
}



 */