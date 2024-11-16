package com.any.some.feature.text

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.feature.whiteboard.presentation.WhiteboardItem
import com.any.some.feature.whiteboard.presentation.WhiteboardItemTransformer
import javax.inject.Inject

class TextWhiteboardItemTransformer @Inject constructor() : WhiteboardItemTransformer<String> {
    override suspend fun invoke(
        item: WhiteboardItemData
    ) = TextWhiteboardItem(
        item.id,
        item.body,
        item.type,
        IntOffset(item.x, item.y),
        DpSize(Dp(item.width), Dp(item.height))
    )

    override suspend fun invoke(
        item: WhiteboardItem<String>
    ) = WhiteboardItemData(
        item.id,
        item.type,
        item.data,
        item.offset.x,
        item.offset.y,
        item.size.width.value,
        item.size.height.value
    )
}