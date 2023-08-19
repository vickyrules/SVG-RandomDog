package com.android.pet_snap.di

import com.android.pet_snap.data.repository.DogImageRepository
import com.android.pet_snap.data.repository.DogImageRepositoryImpl
import com.android.pet_snap.data.repository.GalleryDogEntityRepositoryImpl
import com.android.pet_snap.data.repository.GalleryItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindsDogImageRepository(
        dogImageRepositoryImpl: DogImageRepositoryImpl,
    ): DogImageRepository


    @Binds
    fun bindsGalleryItemRepository(
        galleryDogEntityRepositoryImpl: GalleryDogEntityRepositoryImpl
    ): GalleryItemsRepository

}