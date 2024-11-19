package com.any.some.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "whiteboard_item")
class WhiteboardItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val type: String,
    val body: String,
    val x: Int,
    val y: Int,
    val width: Float,
    val height: Float
)