package com.any.some.presentation.whiteboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AspectRatio
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.presentation.feature.picture.PictureUIWhiteboardItem
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
    var toolbarVisible by remember { mutableStateOf(false) }

    item.ObserveChanges()

    Box(
        Modifier
            .offset { item.offset }
            .pointerInput(item) {
                detectDragGestures { _, dragAmount ->
                    item.offset += dragAmount.round()
                }
            }
            .pointerInput(item) {
                detectTapGestures(
                    onDoubleTap = {
                        toolbarVisible = !toolbarVisible
                    }
                )
            }
            .shadow(4.dp)
            .background(Color(0xFFFED800))
            .padding(16.dp)
    ) {
        Column(
            Modifier.width(item.size.width)
        ) {
            Box(Modifier.size(item.size)) {
                item.Content()
            }
            AnimatedVisibility(visible = toolbarVisible) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    item.Toolbar()
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .pointerInput(item) {
                                detectDragGestures { _, dragAmount ->
                                    val (x, y) = dragAmount
                                    item.size += DpSize(x.toDp(), y.toDp())
                                }
                            }
                    ) {
                        Icon(
                            Icons.Default.AspectRatio,
                            contentDescription = "Expand your whiteboard item"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevWhiteboard() {
    AnySomeTheme {
        Whiteboard(
            Modifier.fillMaxSize(),
            listOf(
                TextUIWhiteboardItem(
                    WhiteboardItemData(
                        0,
                        "",
                        "",
                        0,
                        0,
                        0F,
                        0F
                    )
                ),
                PictureUIWhiteboardItem(
                    WhiteboardItemData(
                        0,
                        "",
                        "",
                        0,
                        0,
                        0F,
                        0F
                    )
                )
            )
        ) { }
    }
}