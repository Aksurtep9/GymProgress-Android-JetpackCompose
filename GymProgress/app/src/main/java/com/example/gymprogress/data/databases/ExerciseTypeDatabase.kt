package com.example.gymprogress.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gymprogress.data.dao.ExerciseTypeDao
import com.example.gymprogress.data.entities.ExerciseTypeEntity

@Database(entities = [ExerciseTypeEntity::class], version = 1, exportSchema = false)
abstract class ExerciseTypeDatabase : RoomDatabase() {
    abstract val dao: ExerciseTypeDao
}