package com.any.some.domain.repository

import com.any.some.domain.model.WhiteboardItem
import kotlinx.coroutines.flow.Flow

interface WhiteboardItemRepository {
    suspend fun <T> insert(item: WhiteboardItem<T>)
    fun get(): Flow<List<WhiteboardItem<*>>>
    suspend fun <T> delete(item: WhiteboardItem<T>)
}