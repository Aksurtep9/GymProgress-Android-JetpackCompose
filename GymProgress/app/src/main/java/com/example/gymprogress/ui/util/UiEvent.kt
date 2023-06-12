package com.example.gymprogress.ui.util

import com.example.gymprogress.ui.model.UiText

sealed class UiEvent {
    object Success: UiEvent()
    data class Failure(val message: UiText): UiEvent()
}