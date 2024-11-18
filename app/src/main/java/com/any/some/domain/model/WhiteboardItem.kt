package com.any.some.domain.model

data class WhiteboardItem<T>(
    val id: Long,
    val type: WhiteboardItemType,
    val body: T,
    val x: Int,
    val y: Int,
    val width: Float,
    val height: Float
)