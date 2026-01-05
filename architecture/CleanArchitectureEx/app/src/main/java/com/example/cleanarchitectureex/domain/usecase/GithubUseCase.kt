package com.example.cleanarchitectureex.domain.usecase

import androidx.paging.PagingData
import com.example.cleanarchitectureex.domain.model.Item
import com.example.cleanarchitectureex.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubUseCase @Inject constructor(private val githubRepository: GithubRepository) {
    suspend fun getRepositories(query: String): Flow<PagingData<Item>> {
        return githubRepository.getRepositories(query)
    }
}