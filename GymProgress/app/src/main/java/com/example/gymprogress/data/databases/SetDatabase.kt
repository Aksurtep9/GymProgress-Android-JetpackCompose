package com.example.gymprogress.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gymprogress.data.dao.SetDao

import com.example.gymprogress.data.entities.SetEntity

@Database(entities = [SetEntity::class], version = 1, exportSchema = false)
abstract class SetDatabase: RoomDatabase() {
    abstract val dao: SetDao
}