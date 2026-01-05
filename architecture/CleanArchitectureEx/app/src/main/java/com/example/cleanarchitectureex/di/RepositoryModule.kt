package com.example.cleanarchitectureex.di

import com.example.cleanarchitectureex.data.datasource.remote.RepositoryService
import com.example.cleanarchitectureex.data.repository.GithubRepositoryImpl
import com.example.cleanarchitectureex.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesGithubRepositories(repositoryService: RepositoryService): GithubRepository =
        GithubRepositoryImpl(repositoryService)
}