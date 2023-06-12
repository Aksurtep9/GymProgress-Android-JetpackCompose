package com.example.gymprogress.domain.model

import com.example.gymprogress.data.entities.SessionEntity
import kotlinx.datetime.LocalDate


data class Session(
    val id: Int,
    val name: String,
    val date: LocalDate,
    val finished: Boolean,
    val longitude: Double,
    val latitude: Double
)

fun SessionEntity.asSession(): Session = Session(
    id = id,
    name = name,
    date = date,
    finished = finished,
    longitude = longitude,
    latitude = latitude
)

fun Session.asSessionEntity(): SessionEntity = SessionEntity(
    id = id,
    name = name,
    date = date,
    finished = finished,
    longitude = longitude,
    latitude = latitude
)