package com.any.some.feature.whiteboard.presentation

import com.any.some.domain.model.WhiteboardItemData

interface WhiteboardItemTransformer<T : Any> {
    suspend operator fun invoke(
        item: WhiteboardItemData
    ): WhiteboardItem<T>

    suspend operator fun invoke(
        item: WhiteboardItem<T>
    ): WhiteboardItemData
}