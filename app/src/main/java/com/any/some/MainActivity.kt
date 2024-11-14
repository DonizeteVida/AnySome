package com.any.some

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.any.some.compose.Whiteboard
import com.any.some.ui.theme.AnySomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnySomeTheme {
                Whiteboard(
                    Modifier.fillMaxSize(),
                    emptyList()
                )
            }
        }
    }
}