package com.example.gymprogress.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gymprogress.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewExerciseTypeDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    var newExerciseType by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = stringResource(id = R.string.new_exercise)) },
            text = {
                OutlinedTextField(
                    value = newExerciseType,
                    onValueChange = { newExerciseType = it },
                    label = { Text(stringResource(id = R.string.new_exercise_type)) }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm(newExerciseType)
                        onDismiss()
                    }
                ) {
                    Text(text = stringResource(id = R.string.Ok))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(id = R.string.Cancel))
                }
            }
        )
}

@Preview
@Composable
fun AddNewExerciseTypeDialogPreview() {
    AddNewExerciseTypeDialog(
        onDismiss = { },
        onConfirm = {  }
    )
}

