package com.any.some.presentation.whiteboard.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.any.some.presentation.model.UIWhiteboardItem
import com.any.some.presentation.text.TextUIWhiteboardItem

@Composable
fun WhiteboardManager(
    state: State<List<UIWhiteboardItem<*>>>,
    onInsert: (UIWhiteboardItem<*>) -> Unit
) {
    var offset = remember { IntOffset.Zero }

    Scaffold(
        floatingActionButton = {
            DraggableFloatActionButton {
                TextUIWhiteboardItem(0, body = "Hello World")
                    .apply {
                        this.offset = it - offset
                        onInsert(this)
                    }
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