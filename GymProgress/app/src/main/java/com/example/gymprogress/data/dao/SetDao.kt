package com.example.gymprogress.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymprogress.data.entities.SetEntity
import kotlinx.coroutines.flow.Flow
import com.example.gymprogress.domain.model.Set

@Dao
interface SetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSet(set: SetEntity)

    @Query("SELECT * FROM sets WHERE exercise_Id = :exerciseId")
    fun getSetsForExercise(exerciseId: Int): Flow<List<SetEntity>>

    @Query("SELECT * FROM sets")
    fun getSets(): Flow<List<SetEntity>>


    @Query("SELECT * FROM sets WHERE id = :setId")
    fun getSetById(setId: Int): Flow<SetEntity>

    @Query("DELETE FROM sets WHERE id = :id")
    suspend fun deleteSetById(id: Int)

    @Query("DELETE FROM sets WHERE exercise_Id = :id")
    suspend fun deleteSetsByExerciseId(id: Int)
}