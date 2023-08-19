package com.android.pet_snap.data.repository

import android.util.Log
import androidx.collection.LruCache
import com.android.pet_snap.data.local.DogDao
import com.android.pet_snap.data.local.DogEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryDogEntityRepositoryImpl @Inject constructor(
    private val dogDao: DogDao,
    private val cache: LruCache<String, DogEntity>
) :
    GalleryItemsRepository {

    override suspend fun insertDog(dog: DogEntity) {
        if (cache.size() == 20) {
            deleteDog(cache.snapshot().keys.first())
        }

        dog.imageUrl?.let { cache.put(it, dog) }

        dogDao.insertDog(dog = dog)
    }

    override suspend fun deleteDog(imageUrl: String) {
        dogDao.deleteDog(imageUrl = imageUrl)
    }

    override fun getAllDogsStream(): Flow<List<DogEntity>> {
        return dogDao.getAllDogsList()
    }

    override suspend fun deleteAllDog() {
        cache.evictAll()
        dogDao.deleteAllDog()
    }

    override suspend fun awakeLruSession(dogs:List<DogEntity>){
        //Log.v("cacheSize", dogs.toString())

        dogs.map { dog ->
            cache.put(dog.imageUrl.toString(),dog)
        }

        //Log.v("cacheSizes", cache.size().toString())
    }
}