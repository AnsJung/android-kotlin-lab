package com.example.cleanarchitectureex.domain.repository

import androidx.paging.PagingData
import com.example.cleanarchitectureex.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
suspend fun getRepositories(q: String): Flow<PagingData<Item>>
}