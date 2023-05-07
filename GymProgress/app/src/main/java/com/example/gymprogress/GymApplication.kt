package com.example.gymprogress

import android.app.Application
import androidx.room.Room
import com.example.gymprogress.data.databases.ExerciseDatabase
import com.example.gymprogress.data.databases.ExerciseTypeDatabase
import com.example.gymprogress.data.databases.SessionDatabase
import com.example.gymprogress.data.databases.SetDatabase
import com.example.gymprogress.data.repository.ExerciseRepository
import com.example.gymprogress.data.repository.ExerciseRepositoryImpl
import com.example.gymprogress.data.repository.ExerciseTypeRepository
import com.example.gymprogress.data.repository.ExerciseTypeRepositoryImpl
import com.example.gymprogress.data.repository.SessionRepository
import com.example.gymprogress.data.repository.SessionRepositoryImpl
import com.example.gymprogress.data.repository.SetRepository
import com.example.gymprogress.data.repository.SetRepositoryImpl

class GymApplication: Application() {

    companion object {
        private lateinit var sessionDB: SessionDatabase
        private lateinit var exerciseDB: ExerciseDatabase
        private lateinit var exerciseTypeDB: ExerciseTypeDatabase
        private lateinit var setDB: SetDatabase

        lateinit var sessionRepository: SessionRepository
        lateinit var exerciseRepository: ExerciseRepository
        lateinit var exerciseTypeRepository: ExerciseTypeRepository
        lateinit var setRepository: SetRepository
    }

    override fun onCreate() {
        super.onCreate()
        sessionDB = Room.databaseBuilder(
            applicationContext,
            SessionDatabase::class.java,
            "session_database"
        ).fallbackToDestructiveMigration().build()

        sessionRepository = SessionRepositoryImpl(sessionDB.dao)
/*
        exerciseDB = Room.databaseBuilder(
            applicationContext,
            ExerciseDatabase::class.java,
            "exercise_database"
        ).fallbackToDestructiveMigration().build()

        exerciseRepository = ExerciseRepositoryImpl(exerciseDB.dao)

        exerciseTypeDB = Room.databaseBuilder(
            applicationContext,
            ExerciseTypeDatabase::class.java,
            "exerciseType_database"
        ).fallbackToDestructiveMigration().build()

        exerciseTypeRepository = ExerciseTypeRepositoryImpl(exerciseTypeDB.dao)

        setDB = Room.databaseBuilder(
            applicationContext,
            SetDatabase::class.java,
            "set_database"
        ).fallbackToDestructiveMigration().build()

        setRepository = SetRepositoryImpl(setDB.dao)
        */
    }


}