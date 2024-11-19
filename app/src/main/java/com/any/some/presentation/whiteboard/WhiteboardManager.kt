package com.any.some.presentation.whiteboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.presentation.compose.DraggableFloatActionButton
import com.any.some.presentation.model.UIWhiteboardItem
import kotlinx.coroutines.channels.Channel

val LocalWhiteboardController = staticCompositionLocalOf<WhiteboardController> {
    error("Should be provided at runtime")
}

@Composable
fun WhiteboardManager() {
    val whiteboardController = LocalWhiteboardController.current

    val uiItems = remember { mutableStateListOf<UIWhiteboardItem<*>>() }
    val newItems = remember { Channel<WhiteboardItemData<*>>() }

    var whiteboardOffset = remember { IntOffset.Zero }

    LaunchedEffect(uiItems) {
        uiItems += whiteboardController.getAll()
    }

    LaunchedEffect(newItems) {
        for (newItem in newItems) {
            val databaseItem = whiteboardController.insert(newItem)
            val newUIItem = whiteboardController.getUIItem(databaseItem)
            uiItems += newUIItem
        }
    }

    Scaffold(
        floatingActionButton = {
            DraggableFloatActionButton { fabOffset ->
                newItems.trySend(
                    whiteboardController.new(fabOffset - whiteboardOffset)
                )
            }
        }
    ) { padding ->
        Whiteboard(
            Modifier
                .fillMaxSize()
                .padding(padding),
            uiItems,
            onDragGesture = { whiteboardOffset = it }
        )
    }
}