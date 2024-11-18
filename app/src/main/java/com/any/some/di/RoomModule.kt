package com.any.some.di

import android.content.Context
import androidx.room.Room
import com.any.some.data.room.AnySomeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AnySomeDatabase::class.java,
        "any_some.db"
    ).build()

    @Provides
    fun providesWhiteboardItemDao(
        anySomeDatabase: AnySomeDatabase
    ) = anySomeDatabase.whiteboardItemDataDao
}