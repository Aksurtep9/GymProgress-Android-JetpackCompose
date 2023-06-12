package com.example.gymprogress.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gymprogress.data.converters.LocalDateConverter
import com.example.gymprogress.data.dao.SetDao
import com.example.gymprogress.data.entities.ExerciseEntity
import com.example.gymprogress.data.entities.SessionEntity

import com.example.gymprogress.data.entities.SetEntity

@Database(entities = [SetEntity::class, ExerciseEntity::class, SessionEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class SetDatabase: RoomDatabase() {
    abstract val dao: SetDao
}