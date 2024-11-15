package com.any.some.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.any.some.feature.text.WhiteboardTextItem

@Composable
fun WhiteboardManager() {
    var offset = remember { IntOffset.Zero }
    val data = remember { mutableStateListOf<WhiteboardItem>() }

    Scaffold(
        floatingActionButton = {
            DraggableFloatActionButton {
                data += WhiteboardTextItem(
                    "Hello World"
                ).setOffset(it - offset)
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