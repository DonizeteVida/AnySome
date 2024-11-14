package com.any.some.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.any.some.ui.theme.AnySomeTheme
import kotlin.random.Random

interface WhiteboardItem {
    var x: Int
    var y: Int

    @Composable
    fun Compose()
}

@Composable
fun Whiteboard(
    modifier: Modifier = Modifier,
    items: List<WhiteboardItem>
) {
    var x by remember { mutableIntStateOf(0) }
    var y by remember { mutableIntStateOf(0) }

    Box(
        modifier.pointerInput(Unit) {
            detectDragGestures { _, dragAmount ->
                x += dragAmount.x.toInt()
                y += dragAmount.y.toInt()
            }
        }
    ) {
        for (item in items) {
            Box(
                Modifier
                    .offset { IntOffset(item.x + x, item.y + y) }
                    .pointerInput(Unit) {
                        detectDragGestures { _, dragAmount ->
                            item.x += dragAmount.x.toInt()
                            item.y += dragAmount.y.toInt()
                        }
                    }
            ) {
                item.Compose()
            }
        }
    }
}

private class SimpleWhiteboardItem(
    x: Int = 0,
    y: Int = 0,
    private val size: Int = 0
) : WhiteboardItem {
    override var x: Int by mutableIntStateOf(x)
    override var y: Int by mutableIntStateOf(y)

    private val color = Color((0xFF shl 24) or (Random(size).nextInt() and 0xFFFFFF))

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
            SimpleWhiteboardItem(it, it, (it + 1) * 10)
        })
    }
}