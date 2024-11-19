package com.any.some.presentation.mapper

import com.any.some.domain.model.WhiteboardItemData
import com.any.some.presentation.model.UIWhiteboardItem
import javax.inject.Inject

class UIWhiteboardItemMapperManager @Inject constructor(
    private val uiWhiteboardItemMappers: Map<Class<*>, @JvmSuppressWildcards UIWhiteboardItemMapper<*>>
) {
    @Suppress("UNCHECKED_CAST")
    suspend fun <T> toUIWhiteboardItem(item: WhiteboardItemData<T>): UIWhiteboardItem<T> {
        val clazz = Class.forName(item.type)
        val mapper = uiWhiteboardItemMappers[clazz] as? UIWhiteboardItemMapper<T>
            ?: throw IllegalStateException("Mapper not found for $clazz")
        return mapper.invoke(item)
    }
}