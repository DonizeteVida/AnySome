package com.any.some.presentation.whiteboard

import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.presentation.model.UIWhiteboardItem

interface WhiteboardController {
    fun new(offset: IntOffset): WhiteboardItemData<*>
    suspend fun getAll(): List<UIWhiteboardItem<*>>
    suspend fun <T> insert(item: WhiteboardItemData<T>): WhiteboardItemData<T>
    suspend fun <T> getUIItem(item: WhiteboardItemData<T>): UIWhiteboardItem<T>
}