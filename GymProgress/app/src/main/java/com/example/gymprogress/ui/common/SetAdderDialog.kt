package com.example.gymprogress.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymprogress.R
import com.example.gymprogress.ui.model.ExerciseUi
import com.example.gymprogress.ui.model.SetUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetAdderDialog(
    exercise: ExerciseUi,
    onConfirm: (SetUi) -> Unit,
    onDismiss: () -> Unit
) {
    var count by remember { mutableStateOf(0) }
    var weight by remember { mutableStateOf(0.0f) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(id = R.string.add_set)) },
        text = {
            Column(Modifier.padding(vertical = 8.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextField(
                        value = weight.toString(),
                        onValueChange = {
                            var _weight = it
                            weight = _weight.toFloat()
                        },
                        label = { Text(text = stringResource(id = R.string.weight)) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.width(100.dp)
                    )
                }


                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(
                        onClick = { count-- },
                        enabled = count > 0,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = "-")
                    }
                    Text(text = count.toString(), modifier = Modifier.padding(vertical = 14.dp, horizontal = 8.dp))
                    Button(
                        onClick = { count++ },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(text = "+")
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(SetUi(exerciseId = exercise.id, weight = weight, reps = count)) }) {
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

