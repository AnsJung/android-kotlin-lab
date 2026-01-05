package com.example.cleanarchitectureex.di

import com.example.cleanarchitectureex.data.datasource.remote.RepositoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun providesBaseUrl() = "https://api.github.com"

    @Provides
    @Singleton
    fun providesGsonFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesRetrofit(
        baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build();
    }

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit): RepositoryService =
        retrofit.create(RepositoryService::class.java)
}