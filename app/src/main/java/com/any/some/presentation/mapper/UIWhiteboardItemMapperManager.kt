package com.any.some.presentation.mapper

import com.any.some.domain.model.WhiteboardItem
import com.any.some.presentation.model.UIWhiteboardItem
import javax.inject.Inject

class UIWhiteboardItemMapperManager @Inject constructor(
    private val uiWhiteboardItemMappers: Map<Class<*>, @JvmSuppressWildcards UIWhiteboardItemMapper<*>>
) {
    @Suppress("UNCHECKED_CAST")
    suspend fun <T> toUIWhiteboardItem(item: WhiteboardItem<T>): UIWhiteboardItem<T> {
        val clazz = Class.forName(item.type)
        val mapper = uiWhiteboardItemMappers[clazz] as? UIWhiteboardItemMapper<T>
            ?: throw IllegalStateException("Mapper not found for $clazz")
        return mapper.invoke(item)
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun <T> toWhiteboardItem(item: UIWhiteboardItem<T>): WhiteboardItem<T> {
        val clazz = item::class.java
        val mapper = uiWhiteboardItemMappers[clazz] as? UIWhiteboardItemMapper<T>
            ?: throw IllegalStateException("Mapper not found for $clazz")
        return mapper.invoke(item)
    }
}