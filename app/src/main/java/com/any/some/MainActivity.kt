package com.any.some

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.any.some.feature.whiteboard.ui.WhiteboardManager
import com.any.some.ui.theme.AnySomeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnySomeTheme {
                WhiteboardManager()
            }
        }
    }
}

@Preview
@Composable
private fun MainPrev() {
    AnySomeTheme {
        WhiteboardManager()
    }
}

