package com.any.some.presentation

import com.any.some.domain.model.WhiteboardItemData

interface WhiteboardItemManager<T : Any> {
    suspend operator fun invoke(
        item: WhiteboardItemData
    ): WhiteboardItem<T>

    suspend operator fun invoke(
        item: WhiteboardItem<T>
    ): WhiteboardItemData
}