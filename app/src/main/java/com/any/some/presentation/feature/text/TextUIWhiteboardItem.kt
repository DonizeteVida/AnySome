package com.any.some.presentation.feature.text

import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.any.some.presentation.model.UIWhiteboardItem
import com.any.some.ui.theme.AnySomeTheme

class TextUIWhiteboardItem(
    id: Long = 0,
    body: String = "",
    offset: IntOffset = IntOffset.Zero,
    size: DpSize = DpSize(250.dp, 200.dp)
) : UIWhiteboardItem<String>(
    id,
    body,
    offset.x,
    offset.y,
    size.width.value,
    size.height.value
) {
    @Composable
    override fun Content() {
        TextWhiteboardItem()
    }
}

@Composable
private fun UIWhiteboardItem<String>.TextWhiteboardItem() {
    OutlinedTextField(body, { body = it }, Modifier.size(size))
}

@Preview
@Composable
private fun PrevWhiteboardText() {
    AnySomeTheme {
        val item = TextUIWhiteboardItem(0, body = "Hello World")
        item.Content()
    }
}