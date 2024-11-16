package com.any.some.domain.repository

import com.any.some.domain.model.WhiteboardItemData
import kotlinx.coroutines.flow.Flow

interface WhiteboardItemDataRepository {
    suspend fun insert(item: WhiteboardItemData)
    fun get(): Flow<List<WhiteboardItemData>>
    suspend fun delete(item: WhiteboardItemData)
}