package com.any.some.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "whiteboard_item")
class WhiteboardItemEntity(
    @PrimaryKey
    val id: Long,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val body: String
)