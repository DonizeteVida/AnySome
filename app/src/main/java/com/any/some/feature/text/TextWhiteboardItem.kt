package com.any.some.feature.text

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.any.some.domain.model.WhiteboardItemType
import com.any.some.presentation.WhiteboardItem
import com.any.some.ui.theme.AnySomeTheme

class TextWhiteboardItem(
    id: Long,
    data: String,
    type: WhiteboardItemType = WhiteboardItemType.TEXT,
    offset: IntOffset = IntOffset.Zero,
    size: IntSize = IntSize.Zero
) : WhiteboardItem<String>(id, type, data, offset, size) {
    @Composable
    override fun Content(modifier: Modifier) {
        TextWhiteboardItem(modifier)
    }
}

@Composable
fun WhiteboardItem<String>.TextWhiteboardItem(
    modifier: Modifier
) {
    OutlinedTextField(data, ::setData, modifier)
}

@Preview
@Composable
private fun PrevWhiteboardText() {
    AnySomeTheme {
        val item = TextWhiteboardItem(0, "Hello World")
        item.Content(
            Modifier
                .width(100.dp)
                .height(300.dp)
        )
    }
}