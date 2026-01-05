package com.example.studymvi.repository

import com.example.studymvi.model.Image
import com.example.studymvi.network.RetrofitManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    ImageRepository {

    override suspend fun getRandomImage() = withContext(dispatcher) {
        RetrofitManager.imageService.getRandomImageSuspend().let {
            Image(it.urls.regular, it.color)
        }
    }
}