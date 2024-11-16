package com.any.some

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.any.some.compose.WhiteboardManager
import com.any.some.domain.model.WhiteboardItem
import com.any.some.domain.repository.WhiteboardRepository
import com.any.some.ui.theme.AnySomeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var whiteboardRepository: WhiteboardRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnySomeTheme {
                WhiteboardManager()
            }
        }

        lifecycleScope.launch {
            whiteboardRepository.insert(
                WhiteboardItem(0, 0, 0, 0, 0, "")
            )
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

