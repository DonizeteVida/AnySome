package com.any.some.presentation.feature.picture

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItem
import com.any.some.presentation.mapper.UIWhiteboardItemMapper
import com.any.some.presentation.model.UIWhiteboardItem
import javax.inject.Inject

class PictureUIWhiteboardItemMapper @Inject constructor() : UIWhiteboardItemMapper<String> {
    override fun new(offset: IntOffset) = PictureUIWhiteboardItem(
        offset = offset
    )

    override suspend fun invoke(
        item: WhiteboardItem<String>
    ): UIWhiteboardItem<String> = PictureUIWhiteboardItem(
        item.id,
        item.type,
        item.body,
        IntOffset(item.x, item.y),
        DpSize(Dp(item.width), Dp(item.height))
    )

    override suspend fun invoke(
        item: UIWhiteboardItem<String>
    ) = WhiteboardItem<String>(
        item.id,
        item.type,
        item.body,
        item.offset.x,
        item.offset.y,
        item.size.width.value,
        item.size.height.value
    )
}