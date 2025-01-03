package com.any.some.di

import com.any.some.data.repository.WhiteboardItemRepositoryImpl
import com.any.some.domain.repository.WhiteboardItemRepository
import com.any.some.presentation.whiteboard.WhiteboardController
import com.any.some.presentation.whiteboard.WhiteboardControllerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsWhiteboardItemDataRepositoryImpl(
        impl: WhiteboardItemRepositoryImpl
    ): WhiteboardItemRepository

    @Binds
    fun bindsWhiteboardControllerImpl(
        impl: WhiteboardControllerImpl
    ): WhiteboardController
}