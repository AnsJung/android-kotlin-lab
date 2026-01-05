package com.example.cleanarchitectureex.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

//    @Provides
//    @Singleton
//    fun providesRemoteDataSource(repositoryService: RepositoryService) : RepositoryDataSource{
//        return RepositoryDataSource(repositoryService)
//    }
}