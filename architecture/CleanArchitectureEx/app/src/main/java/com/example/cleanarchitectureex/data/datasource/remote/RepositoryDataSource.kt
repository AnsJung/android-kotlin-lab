//package com.example.cleanarchitectureex.data.datasource.remote
//
//import com.example.cleanarchitectureex.data.model.ItemDTO
//import javax.inject.Inject
//
//class RemoteDataSource @Inject constructor(private val repositoryService: RepositoryService) {
//    suspend fun getRepositories(q:String): ApiResult<List<ItemDTO>> {
//        return try {
//            val response = repositoryService.getRepositories(q)
//            if (response.isSuccessful && response.body() != null) {
//                ApiResult.Success(response.body()!!.items)
//            } else {
//                ApiResult.Failure(Exception("Error: ${response.code()}"))
//            }
//        } catch (e: Exception) {
//            ApiResult.Failure(e)
//        }
//    }
//}