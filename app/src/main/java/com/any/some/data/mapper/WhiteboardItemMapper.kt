package com.any.some.data.mapper

import com.any.some.data.room.entity.WhiteboardItemEntity
import com.any.some.domain.model.WhiteboardItemData

interface WhiteboardItemMapper<T> {
    suspend operator fun invoke(item: WhiteboardItemEntity): WhiteboardItemData<T>
    suspend operator fun invoke(item: WhiteboardItemData<T>): WhiteboardItemEntity
}