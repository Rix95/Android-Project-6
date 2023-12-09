package com.codepath.articlesearch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


    @Entity(tableName = "water_table")
    data class WaterEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "date") val date: String?,
        @ColumnInfo(name = "quantity") val quantity: Long?,
        @ColumnInfo(name = "unit") val unit: String?,
    )
