package com.any.some.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItemType

abstract class WhiteboardItem<T : Any>(
    val id: Long,
    val type: WhiteboardItemType,
    data: T,
    offset: IntOffset = IntOffset.Zero,
    size: DpSize = DpSize.Zero
) {
    var data: T by mutableStateOf(data)
    var offset by mutableStateOf(offset)
    var size by mutableStateOf(size)

    @Composable
    abstract fun Content()

    fun setData(data: T) = apply { this.data = data }
    fun setOffset(offset: IntOffset) = apply { this.offset = offset }
    fun setSize(size: DpSize) = apply { this.size = size }
}