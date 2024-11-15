package com.any.some.feature.whiteboard.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.any.some.feature.text.TextWhiteboardItem
import com.any.some.presentation.WhiteboardItem

@Composable
fun WhiteboardManager(
    onInsert: (WhiteboardItem<*>) -> Unit
) {
    var offset = remember { IntOffset.Zero }
    val data = remember { mutableStateListOf<WhiteboardItem<*>>() }

    Scaffold(
        floatingActionButton = {
            DraggableFloatActionButton {
                val item = TextWhiteboardItem(
                    0,
                    "Hello World"
                ).setOffset(it - offset)
                onInsert(item)
                data += item
            }
        }
    ) { padding ->
        Whiteboard(
            Modifier
                .fillMaxSize()
                .padding(padding),
            data,
            onDragGesture = { offset = it }
        )
    }
}