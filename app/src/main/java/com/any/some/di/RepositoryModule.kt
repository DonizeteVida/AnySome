package com.any.some.di

import com.any.some.data.repository.WhiteboardItemDataRepositoryImpl
import com.any.some.domain.repository.WhiteboardItemDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsWhiteboardItemDataRepositoryImpl(
        impl: WhiteboardItemDataRepositoryImpl
    ): WhiteboardItemDataRepository
}