package com.any.some.presentation.feature.text

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItem
import com.any.some.presentation.mapper.UIWhiteboardItemMapper
import com.any.some.presentation.model.UIWhiteboardItem
import javax.inject.Inject

class TextUIWhiteboardItemMapper @Inject constructor() : UIWhiteboardItemMapper<String> {
    override fun new(offset: IntOffset) = TextUIWhiteboardItem(
        offset = offset
    )

    override suspend fun invoke(
        item: WhiteboardItem<String>
    ) = TextUIWhiteboardItem(
        item.id,
        item.body,
        IntOffset(item.x, item.y),
        DpSize(Dp(item.width), Dp(item.height))
    )

    override suspend fun invoke(
        item: UIWhiteboardItem<String>
    ) = WhiteboardItem(
        item.id,
        item::class.qualifiedName ?: throw IllegalStateException("Class name not found"),
        item.body,
        item.offset.x,
        item.offset.y,
        item.size.width.value,
        item.size.height.value
    )
}