package com.any.some.presentation.mapper

import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.presentation.model.UIWhiteboardItem

interface UIWhiteboardItemMapper<T> {
    fun new(offset: IntOffset): WhiteboardItemData<T>
    suspend operator fun invoke(item: WhiteboardItemData<T>): UIWhiteboardItem<T>
}