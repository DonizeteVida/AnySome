package com.any.some.feature.whiteboard.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import com.any.some.LocalWhiteboardItemManager
import com.any.some.domain.model.WhiteboardItemType
import kotlinx.coroutines.delay

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
    fun Content() {
        val whiteboardItemManager = LocalWhiteboardItemManager.current
        LaunchedEffect(data, offset, size) {
            if (id > 0) {
                delay(1000)
                whiteboardItemManager.update(this@WhiteboardItem)
            }
        }
        WhiteboardItemContent()
    }

    @Composable
    abstract fun WhiteboardItemContent()

    fun setData(data: T) = apply { this.data = data }
    fun setOffset(offset: IntOffset) = apply { this.offset = offset }
    fun setSize(size: DpSize) = apply { this.size = size }

    override fun toString(): String {
        return "WhiteboardItem(id=$id, type=$type, data=$data, offset=$offset, size=$size)"
    }
}