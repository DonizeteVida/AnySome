package com.any.some.presentation.model


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.viewmodel.compose.viewModel
import com.any.some.domain.model.WhiteboardItemType
import com.any.some.presentation.whiteboard.presentation.WhiteboardManagerViewModel
import kotlinx.coroutines.delay

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
    fun Content() {
        val viewModel = viewModel<WhiteboardManagerViewModel>()
        LaunchedEffect(body, offset, size) {
            if (id > 0) {
                delay(1000)
                viewModel.updateUIWhiteboardItem(this@UIWhiteboardItem)
            }
        }
        WhiteboardItemContent()
    }

    @Composable
    abstract fun WhiteboardItemContent()
}