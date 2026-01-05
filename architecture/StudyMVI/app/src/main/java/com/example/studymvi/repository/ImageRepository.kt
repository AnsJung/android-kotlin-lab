package com.example.studymvi.repository

import com.example.studymvi.model.Image

interface ImageRepository {

    suspend fun getRandomImage() : Image
}