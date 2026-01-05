package com.example.cleanarchitectureex.data.datasource.remote

import com.example.cleanarchitectureex.data.model.GithubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<GithubResponse>

}