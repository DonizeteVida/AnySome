package com.any.some.presentation.text

import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.any.some.domain.model.WhiteboardItemType
import com.any.some.presentation.model.UIWhiteboardItem
import com.any.some.ui.theme.AnySomeTheme

class TextUIWhiteboardItem(
    id: Long = 0,
    type: WhiteboardItemType = WhiteboardItemType.TEXT,
    body: String = "",
    offset: IntOffset = IntOffset.Zero,
    size: DpSize = DpSize(150.dp, 100.dp)
) : UIWhiteboardItem<String>(
    id,
    type,
    body,
    offset.x,
    offset.y,
    size.width.value,
    size.height.value
) {
    @Composable
    override fun WhiteboardItemContent() {
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