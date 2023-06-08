package com.example.gymprogress.data.dao

import androidx.room.*
import com.example.gymprogress.data.entities.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity)

    @Query("SELECT * FROM exercises WHERE sessionId = :sessionId")
    fun getExercisesForSession(sessionId: Int): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE exerciseId = :exerciseId")
    fun getExerciseById(exerciseId: Int): Flow<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE name = :name")
    fun getExercisesByName(name: String): Flow<List<ExerciseEntity>>

    // Add other queries as needed
    @Query("DELETE FROM exercises WHERE exerciseId = :exerciseId")
    fun deleteExerciseById(exerciseId: Int)

    @Query("DELETE FROM exercises WHERE sessionId = :sessionId")
    fun deleteExerciseBySessionId(sessionId: Int)

    @Query("UPDATE exercises SET maxWeight = :newMaxWeight WHERE exerciseId = :id")
    fun updateMaxWeightForId(newMaxWeight: Float, id: Int)
}