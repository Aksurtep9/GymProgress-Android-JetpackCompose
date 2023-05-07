package com.example.gymprogress.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymprogress.ui.model.ExerciseUi
import com.example.gymprogress.ui.model.SetUi

@Composable
fun ExerciseItem(
    exercise: ExerciseUi,
    onDelete: () -> Unit,

    onAddSetClick: (ExerciseUi) -> Unit,
    onDeleteClick: (ExerciseUi) -> Unit,
    sets: List<SetUi>
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { expanded = !expanded })
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = exercise.name,
                    modifier = Modifier.weight(1f).padding(16.dp)
                )
                IconButton(onClick = { onAddSetClick(exercise) }) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Add Set"
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 20.dp))
                IconButton(onClick = { onDeleteClick(exercise) }) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete Exercise"
                    )
                }
            }

            if (true) {
                Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

                sets.filter { it.exerciseId == exercise.id }.forEach { set ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = "Rep: ${set.reps}\t\t Weight: ${set.weight} kg",
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { onAddSetClick }) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Delete Set"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseItemPreview() {
    val exercise = ExerciseUi(
        id = 1,
        sessionId = 1,
        name = "Bench Press"
    )

    val sets = listOf(
        SetUi(id = 1, exerciseId = 1, reps = 8, weight = 100.0f),
        SetUi(id = 2, exerciseId = 1, reps = 8, weight = 110.0f),
        SetUi(id = 3, exerciseId = 1, reps = 8, weight = 120.0f)
    )

    ExerciseItem(
        exercise = exercise,
        onDelete = { },
        onAddSetClick = { },
        onDeleteClick = { },
        sets = sets
    )
}