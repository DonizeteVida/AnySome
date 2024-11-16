package com.any.some.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.any.some.data.room.dao.WhiteboardItemDataDao
import com.any.some.data.room.entity.WhiteboardItemDataEntity

@Database(
    version = 1,
    entities = [
        WhiteboardItemDataEntity::class
    ]
)
abstract class AnySomeDatabase : RoomDatabase() {
    abstract val whiteboardItemDataDao: WhiteboardItemDataDao
}