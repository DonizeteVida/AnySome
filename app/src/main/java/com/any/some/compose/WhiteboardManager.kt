package com.any.some.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset

@Composable
fun WhiteboardManager() {
    var offset = remember { IntOffset.Zero }
    val data = remember { mutableStateListOf<SimpleWhiteboardItem>() }

    Scaffold(
        floatingActionButton = {
            DraggableFloatActionButton {
                data += SimpleWhiteboardItem(
                    it - offset,
                    75
                )
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