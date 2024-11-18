package com.any.some.presentation.mapper

import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItem
import com.any.some.presentation.model.UIWhiteboardItem

interface UIWhiteboardItemMapper<T> {
    fun new(offset: IntOffset): UIWhiteboardItem<T>
    suspend operator fun invoke(item: UIWhiteboardItem<T>): WhiteboardItem<T>
    suspend operator fun invoke(item: WhiteboardItem<T>): UIWhiteboardItem<T>
}