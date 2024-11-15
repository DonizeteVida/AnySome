package com.any.some.compose

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.round
import com.any.some.ui.theme.AnySomeTheme

abstract class WhiteboardItem {
    var offset by mutableStateOf(IntOffset.Zero)

    @Composable
    abstract fun Content(modifier: Modifier)
}

fun WhiteboardItem.setOffset(offset: IntOffset) = apply {
    this.offset = offset
}

@Composable
fun Whiteboard(
    modifier: Modifier = Modifier,
    items: List<WhiteboardItem>,
    onDragGesture: (IntOffset) -> Unit
) {
    var offset by remember { mutableStateOf(IntOffset.Zero) }

    Box(
        modifier.pointerInput(Unit) {
            detectDragGestures { _, dragAmount ->
                offset += dragAmount.round()
                onDragGesture(offset)
            }
        }
    ) {
        Box(Modifier.offset { offset }) {
            for (item in items) {
                Box(
                    Modifier
                        .offset { item.offset }
                        .pointerInput(Unit) {
                            detectDragGestures { _, dragAmount ->
                                item.offset += dragAmount.round()
                            }
                        }
                ) {
                    item.Content(Modifier)
                }
            }
        }
    }
}

class Teste {
    var a: Int = 0
}

@Preview
@Composable
private fun PrevWhiteboard() {
    AnySomeTheme {

    }
}