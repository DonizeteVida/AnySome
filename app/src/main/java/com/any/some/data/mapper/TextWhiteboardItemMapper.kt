package com.any.some.data.mapper

import com.any.some.data.room.entity.WhiteboardItemEntity
import com.any.some.domain.model.WhiteboardItem
import com.any.some.domain.model.WhiteboardItemType
import javax.inject.Inject

class TextWhiteboardItemMapper @Inject constructor() : WhiteboardItemMapper<String> {
    override suspend fun invoke(
        item: WhiteboardItemEntity
    ) = WhiteboardItem<String>(
        item.id,
        WhiteboardItemType.TEXT,
        item.body,
        item.x,
        item.y,
        item.width,
        item.height
    )

    override suspend fun invoke(
        item: WhiteboardItem<String>
    ) = WhiteboardItemEntity(
        item.id,
        item.type.ordinal,
        item.body,
        item.x,
        item.y,
        item.width,
        item.height
    )
}