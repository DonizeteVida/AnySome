package com.any.some.domain.repository

import com.any.some.domain.model.WhiteboardItem
import kotlinx.coroutines.flow.Flow

interface WhiteboardRepository {
    suspend fun insert(item: WhiteboardItem)
    fun get(): Flow<List<WhiteboardItem>>
    suspend fun delete(item: WhiteboardItem)
}