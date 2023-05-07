package com.example.gymprogress.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.gymprogress.domain.model.Session


@Entity(
    tableName = "exercises",
    foreignKeys = [ForeignKey(entity = SessionEntity::class, parentColumns = ["id"], childColumns = ["sessionId"], onDelete = ForeignKey.CASCADE)])
data class ExerciseEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val sessionId: Int,
    val maxWeight: Float = 0.0f
)