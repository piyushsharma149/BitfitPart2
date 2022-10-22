package com.example.project2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table")
    fun getAll(): Flow<List<FoodEntity>>

    @Query("SELECT AVG(calories) FROM food_table")
    fun getAverage(): Int

    @Insert
    fun insert(food: FoodEntity)

    @Query("DELETE FROM food_table")
    fun deleteAll()
}