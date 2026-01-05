package com.example.studymvi.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {
    @Headers("Authorization: Client-ID _XhdJToiLKvElJCsIRlhq58MpBlAolox5MT9pKWBQE8")
    @GET("random")
    fun getRandomImage(): Call<ImageResponse>

    @Headers("Authorization: Client-ID _XhdJToiLKvElJCsIRlhq58MpBlAolox5MT9pKWBQE8")
    @GET("random")
    fun getRandomImageRx(): Single<ImageResponse>

    @Headers("Authorization: Client-ID _XhdJToiLKvElJCsIRlhq58MpBlAolox5MT9pKWBQE8")
    @GET("random")
    suspend fun getRandomImageSuspend(): ImageResponse
}