package com.any.some.domain.usecase

import com.any.some.domain.model.WhiteboardItemData
import com.any.some.domain.model.WhiteboardItemType
import com.any.some.presentation.WhiteboardItem
import com.any.some.presentation.WhiteboardItemManager
import javax.inject.Inject

class TransformWhiteboardItemDataUseCase @Inject constructor(
    private val whiteboardItemManagers: Map<WhiteboardItemType, @JvmSuppressWildcards WhiteboardItemManager<*>>
) {
    suspend operator fun invoke(item: WhiteboardItemData): WhiteboardItem<*> {
        val manager = whiteboardItemManagers[item.type]
            ?: throw IllegalStateException("Manager not found for type ${item.type}")
        return manager(item)
    }
}