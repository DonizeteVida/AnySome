package com.any.some.domain.model

data class WhiteboardItem(
    val id: Long,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val body: String
)