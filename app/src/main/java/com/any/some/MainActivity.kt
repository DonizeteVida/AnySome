package com.any.some

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.any.some.feature.whiteboard.presentation.WhiteboardManagerViewModel
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
                val viewModel by viewModels<WhiteboardManagerViewModel>()
                val items by viewModel.items.collectAsState()
                WhiteboardManager(
                    items = items,
                    onInsert = viewModel::insertWhiteboardItem
                )
            }
        }
    }
}

@Preview
@Composable
private fun MainPrev() {
    AnySomeTheme {
        WhiteboardManager(
            items = emptyList(),
            onInsert = {

            }
        )
    }
}

