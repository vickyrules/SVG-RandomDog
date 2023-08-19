package com.android.pet_snap.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: DogEntity)

    @Delete
    suspend fun deleteDog(dog: DogEntity)
    @Query("DELETE FROM dogsTable")
    suspend fun deleteAllDog()

    @Query("SELECT * FROM dogsTable")
    fun getAllDogsList():Flow<List<DogEntity>>

}