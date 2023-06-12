package com.example.gymprogress.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymprogress.data.entities.ExerciseTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseTypeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exerciseType: ExerciseTypeEntity)


    @Query("SELECT * FROM exercise_types")
    fun getAll(): Flow<List<ExerciseTypeEntity>>
}