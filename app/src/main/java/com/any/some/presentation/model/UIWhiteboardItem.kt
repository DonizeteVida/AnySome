package com.any.some.presentation.model

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.presentation.whiteboard.LocalWhiteboardController
import kotlinx.coroutines.delay

@Stable
abstract class UIWhiteboardItem<T>(
    val data: WhiteboardItemData<T>
) {
    var body by mutableStateOf(data.body)
    var offset by mutableStateOf(IntOffset(data.x, data.y))
    var size by mutableStateOf(DpSize(Dp(data.width), Dp(data.height)))

    fun setOffset(offset: IntOffset) = apply { this.offset = offset }

    @Composable
    open fun ObserveChanges() {
        val whiteboardController = LocalWhiteboardController.current

        LaunchedEffect(body, offset, size) {
            delay(200)
            println("Saving")
            whiteboardController.insert(
                data.copy(
                    body = body,
                    x = offset.x,
                    y = offset.y,
                    width = size.width.value,
                    height = size.height.value
                )
            )
        }
    }

    @Composable
    abstract fun Content()

    @Composable
    open fun Toolbar() {
        Spacer(Modifier)
    }
}