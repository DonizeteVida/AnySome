package com.any.some.presentation

import com.any.some.domain.model.WhiteboardItemData
import com.any.some.domain.model.WhiteboardItemType

interface WhiteboardItemManager<T : Any> {
    val type: WhiteboardItemType

    suspend operator fun invoke(
        item: WhiteboardItemData
    ): WhiteboardItem<T>

    suspend operator fun invoke(
        item: WhiteboardItem<T>
    ): WhiteboardItemData
}