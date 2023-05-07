package com.example.gymprogress.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate


@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val date: LocalDate,
    val finished: Boolean,
    val longitude: Double,
    val latitude: Double

)