package com.example.cleanarchitectureex.data.repository

//import com.example.cleanarchitectureex.data.datasource.remote.RepositoryDataSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.cleanarchitectureex.data.datasource.remote.RepositoryPagingSource
import com.example.cleanarchitectureex.data.datasource.remote.RepositoryService
import com.example.cleanarchitectureex.data.repository.ItemMapper.toItem
import com.example.cleanarchitectureex.domain.model.Item
import com.example.cleanarchitectureex.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GithubRepositoryImpl @Inject constructor(private val repositoryService: RepositoryService) :
    GithubRepository {
    override suspend fun getRepositories(q: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { RepositoryPagingSource(repositoryService, q) }
        ).flow.map { pagingData ->
            pagingData.map { itemDTO ->
                itemDTO.toItem() // ItemDTO를 Item으로 변환
            }
        }
    }
}