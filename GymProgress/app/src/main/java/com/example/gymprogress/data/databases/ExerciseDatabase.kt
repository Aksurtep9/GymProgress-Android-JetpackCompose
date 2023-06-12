package com.example.gymprogress.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gymprogress.data.converters.LocalDateConverter
import com.example.gymprogress.data.dao.ExerciseDao
import com.example.gymprogress.data.entities.ExerciseEntity
import com.example.gymprogress.data.entities.SessionEntity

@Database(entities = [ExerciseEntity::class, SessionEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class ExerciseDatabase: RoomDatabase() {
    abstract val dao: ExerciseDao
}