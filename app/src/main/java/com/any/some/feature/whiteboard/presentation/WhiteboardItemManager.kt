package com.any.some.feature.whiteboard.presentation

import com.any.some.domain.repository.WhiteboardItemDataRepository
import com.any.some.domain.usecase.InsertWhiteboardItemUseCase
import com.any.some.domain.usecase.TransformWhiteboardItemDataUseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WhiteboardItemManager @Inject constructor(
    private val whiteboardItemDataRepository: WhiteboardItemDataRepository,
    private val insertWhiteboardItemUseCase: InsertWhiteboardItemUseCase,
    private val transformWhiteboardItemDataUseCase: TransformWhiteboardItemDataUseCase
) {
    val items = whiteboardItemDataRepository.get().map { items ->
        items.map { transformWhiteboardItemDataUseCase(it) }
    }

    suspend fun insert(item: WhiteboardItem<*>) {
        insertWhiteboardItemUseCase(item)
    }

    suspend fun update(item: WhiteboardItem<*>) {
        insertWhiteboardItemUseCase(item)
    }
}