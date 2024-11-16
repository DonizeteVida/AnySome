package com.any.some.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.any.some.data.room.entity.WhiteboardItemDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WhiteboardItemDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: WhiteboardItemDataEntity)

    @Query("SELECT * FROM whiteboard_item_data")
    fun get(): Flow<List<WhiteboardItemDataEntity>>

    @Delete
    suspend fun delete(item: WhiteboardItemDataEntity)
}