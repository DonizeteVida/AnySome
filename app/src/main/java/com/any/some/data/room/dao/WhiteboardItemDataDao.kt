package com.any.some.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.any.some.data.room.entity.WhiteboardItemEntity

@Dao
interface WhiteboardItemDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: WhiteboardItemEntity): Long

    @Query("SELECT * FROM whiteboard_item")
    suspend fun getAll(): List<WhiteboardItemEntity>

    @Delete
    suspend fun delete(item: WhiteboardItemEntity)
}