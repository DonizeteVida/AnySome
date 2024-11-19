package com.any.some

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import com.any.some.presentation.whiteboard.LocalWhiteboardController
import com.any.some.presentation.whiteboard.WhiteboardController
import com.any.some.presentation.whiteboard.WhiteboardManager
import com.any.some.ui.theme.AnySomeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var whiteboardController: WhiteboardController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnySomeTheme {
                CompositionLocalProvider(
                    LocalWhiteboardController provides whiteboardController
                ) {
                    WhiteboardManager()
                }
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

