package com.any.some.presentation.feature.picture

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.presentation.mapper.UIWhiteboardItemMapper
import javax.inject.Inject

class PictureUIWhiteboardItemMapper @Inject constructor() : UIWhiteboardItemMapper<String> {
    override fun new(
        offset: IntOffset
    ) = WhiteboardItemData(
        id = 0,
        type = PictureUIWhiteboardItem::class.qualifiedName
            ?: throw IllegalStateException("Class name not found"),
        body = "",
        x = offset.x,
        y = offset.y,
        width = 150.dp.value,
        height = 150.dp.value
    )

    override suspend fun invoke(
        item: WhiteboardItemData<String>
    ) = PictureUIWhiteboardItem(
        WhiteboardItemData(
            id = item.id,
            type = item.type,
            body = item.body,
            x = item.x,
            y = item.y,
            width = item.width,
            height = item.height
        )
    )
}