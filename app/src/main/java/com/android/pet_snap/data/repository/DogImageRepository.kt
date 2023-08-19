package com.android.pet_snap.data.repository

import com.android.pet_snap.network.DogResponse

interface DogImageRepository {
    suspend fun getRandomImage():DogResponse
}