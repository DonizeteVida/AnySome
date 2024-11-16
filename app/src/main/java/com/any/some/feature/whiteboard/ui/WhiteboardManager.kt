package com.any.some.feature.whiteboard.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.any.some.feature.text.TextWhiteboardItem
import com.any.some.presentation.WhiteboardItem

@Composable
fun WhiteboardManager(
    items: List<WhiteboardItem<*>>,
    onInsert: (WhiteboardItem<*>) -> Unit
) {
    var offset = remember(Unit) { IntOffset.Zero }

    Scaffold(
        floatingActionButton = {
            DraggableFloatActionButton {
                TextWhiteboardItem(0, "Hello World")
                    .setOffset(it - offset)
                    .also(onInsert)
            }
        }
    ) { padding ->
        Whiteboard(
            Modifier
                .fillMaxSize()
                .padding(padding),
            items,
            onDragGesture = { offset = it }
        )
    }
}