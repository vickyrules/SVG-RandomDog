package com.android.pet_snap.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

 @Entity(tableName = "DogsTable")
data class DogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val status: String,
    val imageUrl: String?,
)
