package com.example.gymprogress.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_types")
data class ExerciseTypeEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
    )