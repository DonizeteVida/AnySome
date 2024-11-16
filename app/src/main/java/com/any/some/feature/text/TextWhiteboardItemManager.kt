package com.any.some.feature.text

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.presentation.WhiteboardItem
import com.any.some.presentation.WhiteboardItemManager
import javax.inject.Inject

class TextWhiteboardItemManager @Inject constructor() : WhiteboardItemManager<String> {
    //TODO(enum later)
    override val type = 1

    override suspend fun invoke(
        item: WhiteboardItemData
    ) = TextWhiteboardItem(
        item.id,
        item.body,
        IntOffset(item.x, item.y),
        IntSize(item.width, item.height)
    )

    override suspend fun invoke(
        item: WhiteboardItem<String>
    ) = WhiteboardItemData(
        item.id,
        type,
        item.data,
        item.offset.x,
        item.offset.y,
        item.size.width,
        item.size.height
    )
}