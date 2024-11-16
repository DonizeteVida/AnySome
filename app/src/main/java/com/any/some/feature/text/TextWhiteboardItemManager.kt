package com.any.some.feature.text

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.domain.model.WhiteboardItemType
import com.any.some.presentation.WhiteboardItem
import com.any.some.presentation.WhiteboardItemManager
import javax.inject.Inject

class TextWhiteboardItemManager @Inject constructor() : WhiteboardItemManager<String> {
    override val type = WhiteboardItemType.TEXT

    override suspend fun invoke(
        item: WhiteboardItemData
    ) = TextWhiteboardItem(
        item.id,
        item.body,
        item.type,
        IntOffset(item.x, item.y),
        IntSize(item.width, item.height)
    )

    override suspend fun invoke(
        item: WhiteboardItem<String>
    ) = WhiteboardItemData(
        item.id,
        item.type,
        item.data,
        item.offset.x,
        item.offset.y,
        item.size.width,
        item.size.height
    )
}