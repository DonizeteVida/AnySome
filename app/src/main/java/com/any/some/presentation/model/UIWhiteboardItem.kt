package com.any.some.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItemType

abstract class UIWhiteboardItem<T>(
    val id: Long,
    val type: WhiteboardItemType,
    body: T,
    x: Int,
    y: Int,
    width: Float,
    height: Float
) {
    var body by mutableStateOf(body)
    var offset by mutableStateOf(IntOffset(x, y))
    var size by mutableStateOf(DpSize(Dp(width), Dp(height)))

    @Composable
    abstract fun Content()

    @Composable
    open fun Toolbar() {
        // Implement your additional toolbar items
    }
}