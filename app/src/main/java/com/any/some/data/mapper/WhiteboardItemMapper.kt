package com.any.some.data.mapper

import com.any.some.data.room.entity.WhiteboardItemEntity
import com.any.some.domain.model.WhiteboardItem

interface WhiteboardItemMapper<T> {
    suspend operator fun invoke(item: WhiteboardItemEntity): WhiteboardItem<T>
    suspend operator fun invoke(item: WhiteboardItem<T>): WhiteboardItemEntity
}