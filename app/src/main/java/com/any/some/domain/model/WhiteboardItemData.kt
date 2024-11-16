package com.any.some.domain.model

data class WhiteboardItemData(
    val id: Long,
    val type: WhiteboardItemType,
    val body: String,
    val x: Int,
    val y: Int,
    val width: Float,
    val height: Float
)