package com.example.gymprogress

import android.app.Application
import androidx.room.Room
import com.example.gymprogress.data.databases.ExerciseDatabase
import com.example.gymprogress.data.databases.ExerciseTypeDatabase
import com.example.gymprogress.data.databases.GymDatabase
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
        private lateinit var gymDB: GymDatabase

        lateinit var sessionRepository: SessionRepository
        lateinit var exerciseRepository: ExerciseRepository
        lateinit var exerciseTypeRepository: ExerciseTypeRepository
        lateinit var setRepository: SetRepository
    }

    override fun onCreate() {
        super.onCreate()
        gymDB = Room.databaseBuilder(
            applicationContext,
            GymDatabase::class.java,
            "gym_database"
        ).fallbackToDestructiveMigration().build()

        sessionRepository = SessionRepositoryImpl(gymDB.sessionDao)

        exerciseRepository = ExerciseRepositoryImpl(gymDB.exerciseDao)

        exerciseTypeRepository = ExerciseTypeRepositoryImpl(gymDB.exercisetypeDao)

        setRepository = SetRepositoryImpl(gymDB.setDao)

    }


}