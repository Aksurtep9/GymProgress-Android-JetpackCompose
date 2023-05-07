package com.example.gymprogress.ui.model

import com.example.gymprogress.domain.model.Session
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import java.time.LocalDateTime

data class SessionUi(
    val id: Int = 0,
    val name: String = "",
    val date: String = LocalDate(
        LocalDateTime.now().year,
        LocalDateTime.now().monthValue,
        LocalDateTime.now().dayOfMonth
    ).toString(),
    val finished: Boolean = false,
    val longitude: Double = 0.0,
    val latitude: Double = 0.0
)

fun Session.asSessionUi(): SessionUi = SessionUi(
    id = id,
    name = name,
    date = date.toString(),
    finished = finished,
    longitude = longitude,
    latitude = latitude
)

fun SessionUi.asSession(): Session = Session(
    id = id,
    name = name,
    date = date.toLocalDate(),
    finished = finished,
    longitude = longitude,
    latitude = latitude
)