package com.any.some.presentation.whiteboard.presentation

import com.any.some.domain.repository.WhiteboardItemRepository
import com.any.some.presentation.mapper.UIWhiteboardItemMapperManager
import com.any.some.presentation.model.UIWhiteboardItem
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UIWhiteboardItemManager @Inject constructor(
    private val whiteboardItemRepository: WhiteboardItemRepository,
    private val uiWhiteboardItemMapperManager: UIWhiteboardItemMapperManager
) {
    val items = whiteboardItemRepository.get().map { items ->
        items.map { item -> uiWhiteboardItemMapperManager.toUIWhiteboardItem(item) }
    }

    suspend fun insert(item: UIWhiteboardItem<*>) {
        whiteboardItemRepository.insert(uiWhiteboardItemMapperManager.toWhiteboardItem(item))
    }
}