package com.example.cleanarchitectureex.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cleanarchitectureex.data.model.ItemDTO
import kotlinx.coroutines.delay
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RepositoryPagingSource @Inject constructor(
    private val repositoryService: RepositoryService,
    private val query: String
) :
    PagingSource<Int, ItemDTO>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemDTO> {
        val position = params.key ?: 1

        return try {
            val response = repositoryService.getRepositories(query, position, params.loadSize)
            val result = processResponse(response)

            if (position != 1) {
                delay(3000)
            }
            when (result) {
                is ApiResult.Success -> {
                    LoadResult.Page(
                        data = result.data.items,
                        prevKey = null,
                        nextKey = position + (params.loadSize / 30)
                    )
                }

                is ApiResult.Error -> {
                    LoadResult.Error(Exception(result.msg))
                }
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun <T> processResponse(response: Response<T>): ApiResult<T> {
        return if (response.isSuccessful) {
            var body = response.body()
            if (body != null) {
                ApiResult.Success(body)
            } else {
                ApiResult.Error("Empty response body")
            }
        } else {
            ApiResult.Error("Error: ${response.code()} ${response.message()}")
        }
    }

}

