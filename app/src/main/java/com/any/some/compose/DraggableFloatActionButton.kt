package com.any.some.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.round

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableFloatActionButton(
    onDragDropped: (IntOffset) -> Unit
) {
    var position = remember { IntOffset.Zero }
    var offset by remember { mutableStateOf(IntOffset.Zero) }

    Surface(
        modifier = Modifier
            .offset { offset }
            .draggable2D(
                rememberDraggable2DState { offset += it.round() },
                onDragStopped = {
                    onDragDropped(position + offset)
                    offset = IntOffset.Zero
                }
            )
            .onGloballyPositioned { coordinates ->
                if (position == IntOffset.Zero) {
                    val (width, height) = coordinates.size / 4
                    val window = coordinates.positionInWindow().round()
                    position = window + IntOffset(width, -height)
                }
            },
        onClick = {

        },
        color = Color.Red
    ) {
        Icon(
            Icons.Default.Edit,
            contentDescription = null
        )
    }
}