package com.any.some.feature.whiteboard.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.any.some.feature.text.TextWhiteboardItem
import com.any.some.feature.whiteboard.presentation.WhiteboardItem

@Composable
fun WhiteboardManager(
    state: State<List<WhiteboardItem<*>>>,
    onInsert: (WhiteboardItem<*>) -> Unit
) {
    var offset = remember { IntOffset.Zero }

    Scaffold(
        floatingActionButton = {
            DraggableFloatActionButton {
                TextWhiteboardItem(0, "Hello World")
                    .setOffset(it - offset)
                    .also(onInsert)
            }
        }
    ) { padding ->
        val items by state
        Whiteboard(
            Modifier
                .fillMaxSize()
                .padding(padding),
            items,
            onDragGesture = { offset = it }
        )
    }
}