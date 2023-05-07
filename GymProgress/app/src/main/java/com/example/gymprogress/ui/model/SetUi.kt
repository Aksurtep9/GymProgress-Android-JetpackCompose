package com.example.gymprogress.ui.model

import kotlinx.datetime.LocalDate
import java.time.LocalDateTime

data class SetUi(
    val id: Int,
    val exerciseId: Int,
    val weight: Float,
    val reps: Int
)