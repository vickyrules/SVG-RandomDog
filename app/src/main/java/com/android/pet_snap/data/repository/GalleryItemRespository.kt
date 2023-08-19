package com.android.pet_snap.data.repository

import com.android.pet_snap.data.local.DogEntity
import kotlinx.coroutines.flow.Flow

interface GalleryItemsRepository {
    /**
     * get all items from the data source
     */

    fun getAllDogsStream(): Flow<List<DogEntity>>


    /**
     * Insert item in the data source
     */
    suspend fun insertDog(dog: DogEntity)

    /**
     * Delete item from the data source
     */
    suspend fun deleteDog(imageUrl:String)

    /**
     * Delete all items from the data source
     */
    suspend fun deleteAllDog()

    suspend fun awakeLruSession(dogs:List<DogEntity>)

}
