package com.any.some.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

abstract class WhiteboardItem<T : Any>(
    val id: Long,
    data: T,
    offset: IntOffset = IntOffset.Zero,
    size: IntSize = IntSize.Zero
) {
    var data: T by mutableStateOf(data)
    var offset by mutableStateOf(offset)
    var size by mutableStateOf(size)

    @Composable
    abstract fun Content(modifier: Modifier)

    fun setData(data: T) = apply { this.data = data }
    fun setOffset(offset: IntOffset) = apply { this.offset = offset }
    fun setSize(size: IntSize) = apply { this.size = size }
}