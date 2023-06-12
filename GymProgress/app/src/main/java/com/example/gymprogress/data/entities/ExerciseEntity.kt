package com.example.gymprogress.data.entities

import androidx.room.*
import com.example.gymprogress.domain.model.Session


@Entity(
    tableName = "exercises",
    foreignKeys = [ForeignKey(entity = SessionEntity::class, parentColumns = ["id"], childColumns = ["sessionId"], onDelete = ForeignKey.CASCADE)])
data class ExerciseEntity (
    @PrimaryKey(autoGenerate = true) val exerciseId: Int,
    val name: String,
    @ColumnInfo(index = true)
    val sessionId: Int,
    val maxWeight: Float = 0.0f
)