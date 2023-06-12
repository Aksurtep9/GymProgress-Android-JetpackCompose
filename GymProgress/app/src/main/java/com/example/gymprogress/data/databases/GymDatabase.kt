package com.example.gymprogress.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gymprogress.data.converters.LocalDateConverter
import com.example.gymprogress.data.dao.ExerciseDao
import com.example.gymprogress.data.dao.ExerciseTypeDao
import com.example.gymprogress.data.dao.SessionDao
import com.example.gymprogress.data.dao.SetDao
import com.example.gymprogress.data.entities.ExerciseEntity
import com.example.gymprogress.data.entities.ExerciseTypeEntity
import com.example.gymprogress.data.entities.SessionEntity
import com.example.gymprogress.data.entities.SetEntity

@Database(entities = [SetEntity::class, ExerciseEntity::class, SessionEntity::class, ExerciseTypeEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class GymDatabase: RoomDatabase() {
    abstract val setDao: SetDao
    abstract val exerciseDao: ExerciseDao
    abstract val sessionDao: SessionDao
    abstract val exercisetypeDao: ExerciseTypeDao
}