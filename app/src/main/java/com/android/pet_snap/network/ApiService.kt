package com.android.pet_snap.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("random")
    suspend fun getDogImage(): DogResponse
    companion object {
        const val BASE_URL = "https://dog.ceo/api/breeds/image/"
    }
}