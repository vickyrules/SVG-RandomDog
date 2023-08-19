package com.android.pet_snap.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [DogEntity::class], version = 1, exportSchema = true)
abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao

}