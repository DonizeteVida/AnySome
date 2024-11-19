package com.any.some.data.mapper

import com.any.some.data.room.entity.WhiteboardItemEntity
import com.any.some.domain.model.WhiteboardItemData
import javax.inject.Inject

class TextWhiteboardItemMapper @Inject constructor() : WhiteboardItemMapper<String> {
    override suspend fun invoke(
        item: WhiteboardItemEntity
    ) = WhiteboardItemData(
        item.id,
        item.type,
        item.body,
        item.x,
        item.y,
        item.width,
        item.height
    )

    override suspend fun invoke(
        item: WhiteboardItemData<String>
    ) = WhiteboardItemEntity(
        item.id,
        item.type,
        item.body,
        item.x,
        item.y,
        item.width,
        item.height
    )
}