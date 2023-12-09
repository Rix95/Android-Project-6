package com.codepath.articlesearch

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterDao {
    @Query("SELECT * FROM water_table")
    fun getAll(): Flow<List<WaterEntity>>

    @Insert
    fun insertAll(entries: List<WaterEntity>)

    @Insert
    fun insert(entity: WaterEntity)

    @Query("DELETE FROM water_table")
    fun deleteAll()

    @Query("SELECT * FROM water_table")
    fun getAllWaterEntries(): List<WaterEntity>

    @Query("SELECT COUNT(*) FROM water_table")
    fun getNumberOfEntries(): Int

    @Query("SELECT AVG(quantity) FROM water_table")
    fun getAverageLongAttribute(): Double
}