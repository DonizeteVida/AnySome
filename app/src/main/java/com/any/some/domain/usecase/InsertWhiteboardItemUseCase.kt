package com.any.some.domain.usecase

import com.any.some.domain.model.WhiteboardItemType
import com.any.some.domain.repository.WhiteboardItemDataRepository
import com.any.some.feature.whiteboard.presentation.WhiteboardItem
import com.any.some.feature.whiteboard.presentation.WhiteboardItemTransformer
import javax.inject.Inject

class InsertWhiteboardItemUseCase @Inject constructor(
    private val whiteboardItemManagers: Map<WhiteboardItemType, @JvmSuppressWildcards WhiteboardItemTransformer<*>>,
    private val whiteboardItemDataRepository: WhiteboardItemDataRepository
) {
    @Suppress("UNCHECKED_CAST") // we can suppress because I believe in god
    suspend operator fun <T : Any> invoke(item: WhiteboardItem<T>) {
        val manager = whiteboardItemManagers[item.type] as? WhiteboardItemTransformer<T>
            ?: throw IllegalStateException("Manager not found for type ${item.type}")
        val whiteboardItemData = manager(item)
        whiteboardItemDataRepository.insert(whiteboardItemData)
    }
}