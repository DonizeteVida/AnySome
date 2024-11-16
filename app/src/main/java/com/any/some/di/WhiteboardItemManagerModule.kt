package com.any.some.di

import com.any.some.domain.model.WhiteboardItemType
import com.any.some.feature.text.TextWhiteboardItemManager
import com.any.some.presentation.WhiteboardItemManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
annotation class WhiteboardItemManagerQualifier(
    val type: WhiteboardItemType
)

@Module
@InstallIn(SingletonComponent::class)
interface WhiteboardItemManagerModule {
    @Binds
    @WhiteboardItemManagerQualifier(WhiteboardItemType.TEXT)
    fun bindsTextWhiteboardItemManager(
        impl: TextWhiteboardItemManager
    ): WhiteboardItemManager<*>
}