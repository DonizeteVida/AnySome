package com.any.some.feature.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.any.some.compose.WhiteboardItem
import com.any.some.ui.theme.AnySomeTheme

@Composable
fun WhiteboardText(
    modifier: Modifier,
    data: String
) {
    val (text, setText) = remember { mutableStateOf(data) }
    Column(modifier) {
        OutlinedTextField(text, setText)
    }
}

class WhiteboardTextItem(
    private val data: String
) : WhiteboardItem() {
    @Composable
    override fun Content(modifier: Modifier) {
        WhiteboardText(modifier, data)
    }
}

@Preview
@Composable
private fun PrevWhiteboardText() {
    AnySomeTheme {
        WhiteboardText(
            Modifier
                .width(100.dp)
                .height(50.dp),
            "Hello World"
        )
    }
}