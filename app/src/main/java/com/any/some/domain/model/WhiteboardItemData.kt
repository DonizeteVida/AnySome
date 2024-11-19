package com.any.some.domain.model

data class WhiteboardItemData<T>(
    val id: Long = 0,
    val type: String,
    val body: T,
    val x: Int,
    val y: Int,
    val width: Float,
    val height: Float
)