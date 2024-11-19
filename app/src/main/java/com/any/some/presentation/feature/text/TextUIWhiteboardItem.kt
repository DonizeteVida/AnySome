package com.any.some.presentation.feature.text

import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.presentation.model.UIWhiteboardItem
import com.any.some.ui.theme.AnySomeTheme

@Stable
class TextUIWhiteboardItem(
    data: WhiteboardItemData<String>
) : UIWhiteboardItem<String>(data) {
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
        val item = TextUIWhiteboardItem(
            WhiteboardItemData(
                0,
                "",
                "",
                0,
                0,
                0F,
                0F
            )
        )
        item.Content()
    }
}