package com.any.some

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.any.some.ui.theme.AnySomeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnySomeTheme {
                Whiteboard(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Whiteboard(modifier: Modifier = Modifier) {
    class Data(
        var x: Int,
        var y: Int,
        val name: String
    )

    val data = remember {
        mutableStateListOf(
            *List(10) { Data(it, it, it.toString()) }.toTypedArray()
        )
    }

    var scale by remember { mutableFloatStateOf(1F) }
    var x by remember { mutableIntStateOf(0) }
    var y by remember { mutableIntStateOf(0) }

    Surface(
        modifier
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    x += dragAmount.x.toInt()
                    y += dragAmount.y.toInt()
                }
            }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    ) {
        for (item in data) {
            Box(
                Modifier.offset {
                    IntOffset(x + item.x, y + item.y)
                }
            ) {
                val color = (0xFF shl 24) or (Random(item.name.hashCode()).nextInt() and 0xFFFFFF)
                Surface(
                    Modifier
                        .width(100.dp)
                        .height(100.dp),
                    color = Color(color)
                ) {
                    Text(item.name, color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PrevWhiteboard() {
    AnySomeTheme {
        Whiteboard(Modifier.fillMaxSize())
    }
}