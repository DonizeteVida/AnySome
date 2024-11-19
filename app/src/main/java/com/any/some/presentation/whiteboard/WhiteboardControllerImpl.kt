package com.any.some.presentation.whiteboard

import androidx.compose.ui.unit.IntOffset
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.domain.repository.WhiteboardItemRepository
import com.any.some.presentation.mapper.UIWhiteboardItemMapper
import com.any.some.presentation.mapper.UIWhiteboardItemMapperManager
import com.any.some.presentation.model.UIWhiteboardItem
import javax.inject.Inject

class WhiteboardControllerImpl @Inject constructor(
    private val whiteboardItemRepository: WhiteboardItemRepository,
    private val uiWhiteboardItemMapperManager: UIWhiteboardItemMapperManager,
    private val uiWhiteboardItemMappers: Map<Class<*>, @JvmSuppressWildcards UIWhiteboardItemMapper<*>>
) : WhiteboardController {

    override fun new(offset: IntOffset) = uiWhiteboardItemMappers
        .values
        .random()
        .new(offset)

    override suspend fun getAll() = whiteboardItemRepository.getAll().map {
        uiWhiteboardItemMapperManager.toUIWhiteboardItem(it)
    }

    override suspend fun <T> insert(item: WhiteboardItemData<T>): WhiteboardItemData<T> {
        val id = whiteboardItemRepository.insert(item)
        return item.copy(id = id)
    }

    override suspend fun <T> getUIItem(item: WhiteboardItemData<T>): UIWhiteboardItem<T> {
        return uiWhiteboardItemMapperManager.toUIWhiteboardItem(item)
    }
}