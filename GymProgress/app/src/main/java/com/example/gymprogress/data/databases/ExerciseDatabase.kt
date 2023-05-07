package com.example.gymprogress.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gymprogress.data.dao.ExerciseDao
import com.example.gymprogress.data.entities.ExerciseEntity

@Database(entities = [ExerciseEntity::class], version = 1, exportSchema = false)
abstract class ExerciseDatabase: RoomDatabase() {
    abstract val dao: ExerciseDao
}