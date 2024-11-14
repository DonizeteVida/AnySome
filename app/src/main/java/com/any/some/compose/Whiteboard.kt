package com.any.some.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.zIndex
import com.any.some.ui.theme.AnySomeTheme
import kotlin.random.Random

interface WhiteboardItem {
    var x: Int
    var y: Int
    var zIndex: Float

    @Composable
    fun Compose()
}

@Composable
fun Whiteboard(
    modifier: Modifier = Modifier,
    items: List<WhiteboardItem>,
    onDragGesture: (IntOffset) -> Unit
) {
    var nextZIndex by remember { mutableFloatStateOf(0F) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier.pointerInput(Unit) {
            detectDragGestures { _, dragAmount ->
                offset += dragAmount
                onDragGesture(offset.round())
            }
        }
    ) {
        Box(Modifier.offset { offset.round() }) {
            for (item in items) {
                Box(
                    Modifier
                        .offset { IntOffset(item.x, item.y) }
                        .zIndex(item.zIndex)
                        .pointerInput(Unit) {
                            detectDragGestures { _, dragAmount ->
                                item.x += dragAmount.x.toInt()
                                item.y += dragAmount.y.toInt()
                                item.zIndex = ++nextZIndex
                            }
                        }
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onDoubleTap = {
                                    item.zIndex = 0F
                                }
                            )
                        }
                ) {
                    item.Compose()
                }
            }
        }
    }
}

class SimpleWhiteboardItem(
    offset: IntOffset,
    private val size: Int = 0
) : WhiteboardItem {
    override var x by mutableIntStateOf(offset.x)
    override var y by mutableIntStateOf(offset.y)
    override var zIndex by mutableFloatStateOf(0F)

    private val color = Color((0xFF shl 24) or (Random(offset.hashCode()).nextInt() and 0xFFFFFF))

    @Composable
    override fun Compose() {
        Text(
            "Hello World",
            Modifier
                .size(size.dp)
                .background(color)
        )
    }
}

@Preview
@Composable
private fun PrevWhiteboard() {
    AnySomeTheme {
        Whiteboard(Modifier.fillMaxSize(), List(10) {
            SimpleWhiteboardItem(IntOffset(it, it), (it + 1) * 10)
        }) {

        }
    }
}