package com.example.roomflow.module

import com.example.roomflow.database.TextDatabase
import com.example.roomflow.repository.DataRepository
import com.example.roomflow.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(database: TextDatabase): DataRepositoryImpl {
        return DataRepositoryImpl(database)
    }
}