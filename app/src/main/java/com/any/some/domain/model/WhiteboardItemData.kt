package com.any.some.domain.model

data class WhiteboardItemData(
    val id: Long,
    val type: Int,
    val body: String,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int
)