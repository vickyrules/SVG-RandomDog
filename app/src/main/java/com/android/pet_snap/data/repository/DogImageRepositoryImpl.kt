package com.android.pet_snap.data.repository

import com.android.pet_snap.data.local.DogDao
import com.android.pet_snap.data.local.DogEntity
import com.android.pet_snap.data.local.asEntity
import com.android.pet_snap.network.ApiService
import com.android.pet_snap.network.DogResponse
import javax.inject.Inject

class DogImageRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dogDao: DogDao
) : DogImageRepository {
    override suspend fun getRandomImage(): DogResponse {
        val response = apiService.getDogImage()
        return response
    }
}