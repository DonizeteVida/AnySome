package com.any.some


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.round
import com.any.some.compose.SimpleWhiteboardItem
import com.any.some.compose.Whiteboard
import com.any.some.ui.theme.AnySomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnySomeTheme {
                Main(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    val data = remember {
        mutableStateListOf<SimpleWhiteboardItem>()
    }
    Scaffold(
        floatingActionButton = {
            DraggableFloatActionButton { offset ->
                data += SimpleWhiteboardItem(
                    offset.x,
                    offset.y,
                    30
                )
            }
        }
    ) { padding ->
        Whiteboard(
            Modifier
                .fillMaxSize()
                .padding(padding),
            data
        )
    }
}

@Preview
@Composable
private fun MainPrev() {
    AnySomeTheme {
        Main(Modifier.fillMaxSize())
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableFloatActionButton(
    onDragDropped: (IntOffset) -> Unit
) {
    var position = remember { Offset.Zero }
    var offset by remember { mutableStateOf(Offset.Zero) }

    FloatingActionButton(
        modifier = Modifier
            .offset { offset.round() }
            .draggable2D(
                rememberDraggable2DState { offset += it },
                onDragStopped = {
                    onDragDropped(position.round() + offset.round())
                    offset = Offset.Zero
                }
            )
            .onGloballyPositioned { coordinates ->
                if (position == Offset.Zero) {
                    position = coordinates.positionInWindow()
                }
            },
        onClick = {

        }
    ) {
        Icon(
            Icons.Default.Edit,
            contentDescription = null
        )
    }
}