package com.any.some.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.any.some.data.room.dao.WhiteboardItemDao
import com.any.some.data.room.entity.WhiteboardItemEntity

@Database(
    version = 1,
    entities = [
        WhiteboardItemEntity::class
    ]
)
abstract class AnySomeDatabase : RoomDatabase() {
    abstract val whiteboardItemDao: WhiteboardItemDao
}