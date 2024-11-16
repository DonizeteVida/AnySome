package com.any.some.feature.text

import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.any.some.domain.model.WhiteboardItemType
import com.any.some.feature.whiteboard.presentation.WhiteboardItem
import com.any.some.ui.theme.AnySomeTheme

class TextWhiteboardItem(
    id: Long,
    data: String,
    type: WhiteboardItemType = WhiteboardItemType.TEXT,
    offset: IntOffset = IntOffset.Zero,
    size: DpSize = DpSize(150.dp, 100.dp)
) : WhiteboardItem<String>(id, type, data, offset, size) {
    @Composable
    override fun WhiteboardItemContent() {
        TextWhiteboardItem()
    }
}

@Composable
fun WhiteboardItem<String>.TextWhiteboardItem() {
    OutlinedTextField(data, ::setData, Modifier.size(size))
}

@Preview
@Composable
private fun PrevWhiteboardText() {
    AnySomeTheme {
        val item = TextWhiteboardItem(0, "Hello World")
        item.Content()
    }
}