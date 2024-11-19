package com.any.some.domain.repository

import com.any.some.domain.model.WhiteboardItemData

interface WhiteboardItemRepository {
    suspend fun <T> insert(item: WhiteboardItemData<T>): Long
    suspend fun getAll(): List<WhiteboardItemData<*>>
    suspend fun <T> delete(item: WhiteboardItemData<T>)
}