package com.any.some.di

import com.any.some.data.repository.WhiteboardRepositoryImpl
import com.any.some.domain.repository.WhiteboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsWhiteboardRepositoryImpl(
        impl: WhiteboardRepositoryImpl
    ): WhiteboardRepository
}