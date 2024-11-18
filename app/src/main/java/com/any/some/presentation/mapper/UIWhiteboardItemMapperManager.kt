package com.any.some.presentation.mapper

import com.any.some.domain.model.WhiteboardItem
import com.any.some.domain.model.WhiteboardItemType
import com.any.some.presentation.model.UIWhiteboardItem
import javax.inject.Inject

class UIWhiteboardItemMapperManager @Inject constructor(
    private val uiWhiteboardItemMappers: Map<WhiteboardItemType, @JvmSuppressWildcards UIWhiteboardItemMapper<*>>
) {
    @Suppress("UNCHECKED_CAST")
    suspend fun <T> toUIWhiteboardItem(item: WhiteboardItem<T>): UIWhiteboardItem<T> {
        val mapper = uiWhiteboardItemMappers[item.type] as? UIWhiteboardItemMapper<T>
            ?: throw IllegalStateException("Mapper not found for ${item.type}")
        return mapper.invoke(item)
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun <T> toWhiteboardItem(item: UIWhiteboardItem<T>): WhiteboardItem<T> {
        val mapper = uiWhiteboardItemMappers[item.type] as? UIWhiteboardItemMapper<T>
            ?: throw IllegalStateException("Mapper not found for ${item.type}")
        return mapper.invoke(item)
    }
}