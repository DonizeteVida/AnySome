package com.any.some.di

import com.any.some.domain.model.WhiteboardItemType
import com.any.some.feature.text.TextWhiteboardItemTransformer
import com.any.some.feature.whiteboard.presentation.WhiteboardItemTransformer
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@MapKey
annotation class WhiteboardItemManagerKey(
    val value: WhiteboardItemType
)

@Module
@InstallIn(SingletonComponent::class)
interface WhiteboardItemManagerModule {
    @Binds
    @IntoMap
    @WhiteboardItemManagerKey(WhiteboardItemType.TEXT)
    fun bindsTextWhiteboardItemTransformer(
        impl: TextWhiteboardItemTransformer
    ): WhiteboardItemTransformer<*>
}