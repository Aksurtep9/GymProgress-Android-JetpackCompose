package com.example.gymprogress.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.gymprogress.domain.model.Exercise

@Entity(
    tableName = "sets",
    foreignKeys = [ForeignKey(entity = ExerciseEntity::class, parentColumns = ["id"], childColumns = ["exerciseId"], onDelete = ForeignKey.CASCADE)]
)
data class SetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val exerciseId: Int,
    val reps: Int,
    val weight: Float
)