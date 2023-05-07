package com.example.gymprogress.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gymprogress.data.converters.LocalDateConverter
import com.example.gymprogress.data.dao.SessionDao
import com.example.gymprogress.data.entities.SessionEntity

@Database(entities = [SessionEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class SessionDatabase: RoomDatabase() {
    abstract val dao: SessionDao
}