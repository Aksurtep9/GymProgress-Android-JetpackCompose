package com.example.gymprogress.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionAdderDialog(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
)
{
    var sessionName by remember {
        mutableStateOf("")
    }
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Enter Session") },
        text = {
            TextField(
                value = sessionName,
                onValueChange = { sessionName = it },
                label = { Text(text = "Session name") },
                modifier = Modifier.run { fillMaxWidth().padding(16.dp) }
            )
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(sessionName); onDismiss() },
            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
            ) {
                Text(text = "Cancel")
            }
        }
    )
}