

package com.android.pet_snap.data.local


import com.android.pet_snap.network.DogResponse


object DogEntityMapper : EntityMapper<DogResponse, DogEntity> {

  override fun asEntity(domain:DogResponse): DogEntity {
    return DogEntity(
        imageUrl = domain.message,
        status = domain.status
      )
  }

  override fun asDomain(entityList: List<DogEntity>): List<DogResponse> {
    return entityList.map { dog ->
          DogResponse(
            message = dog.imageUrl.toString(),
            status = dog.status
          )

    }
  }

}

fun DogResponse.asEntity(): DogEntity {
  return DogEntityMapper.asEntity(this)
}

fun List<DogEntity>?.asDomain(): List<DogResponse> {
  return DogEntityMapper.asDomain(this.orEmpty())
}

