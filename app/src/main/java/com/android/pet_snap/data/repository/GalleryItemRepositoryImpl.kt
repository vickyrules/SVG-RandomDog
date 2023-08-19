package com.android.pet_snap.data.repository

import com.android.pet_snap.data.local.DogDao
import com.android.pet_snap.data.local.DogEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryDogEntityRepositoryImpl @Inject constructor(private val dogDao: DogDao) : GalleryItemsRepository {

    override suspend fun insertDog(dog: DogEntity) {
        dogDao.insertDog(dog = dog)
    }

    override suspend fun deleteDog(dog: DogEntity){
        dogDao.deleteDog(dog = dog)
    }
    override fun getAllDogsStream(): Flow<List<DogEntity>>{
        return dogDao.getAllDogsList()
    }

    override suspend fun deleteAllDog() {
        dogDao.deleteAllDog()
    }

}