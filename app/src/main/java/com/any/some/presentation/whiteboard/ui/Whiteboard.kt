package com.any.some.presentation.whiteboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import com.any.some.presentation.feature.text.TextUIWhiteboardItem
import com.any.some.presentation.model.UIWhiteboardItem
import com.any.some.ui.theme.AnySomeTheme

@Composable
fun Whiteboard(
    modifier: Modifier = Modifier,
    items: List<UIWhiteboardItem<*>>,
    onDragGesture: (IntOffset) -> Unit
) {
    var offset by remember { mutableStateOf(IntOffset.Zero) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = focusManager::clearFocus
            )
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    offset += dragAmount.round()
                    onDragGesture(offset)
                }
            }
    ) {
        Box(Modifier.offset { offset }) {
            items.forEach { WhiteboardItem(it) }
        }
    }
}

@Composable
private fun WhiteboardItem(item: UIWhiteboardItem<*>) {
    Column(
        Modifier
            .offset { item.offset }
            .pointerInput(item) {
                detectDragGestures { _, dragAmount ->
                    item.offset += dragAmount.round()
                }
            }
            .shadow(4.dp)
            .background(Color.Yellow)
            .padding(16.dp)
    ) {
        item.Content()
        Spacer(Modifier.height(16.dp))
        Image(
            painter = rememberVectorPainter(Icons.Default.Menu),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.End)
                .pointerInput(item) {
                    detectDragGestures { _, dragAmount ->
                        val (x, y) = dragAmount
                        item.size += DpSize(x.toDp(), y.toDp())
                    }
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevWhiteboard() {
    AnySomeTheme {
        Whiteboard(
            Modifier.fillMaxSize(),
            listOf(
                TextUIWhiteboardItem(1, body = "Hello World 1"),
                TextUIWhiteboardItem(1, body = "Hello World 2")
            )
        ) { }
    }
}