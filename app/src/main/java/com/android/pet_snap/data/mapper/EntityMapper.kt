package com.android.pet_snap.data.local

interface EntityMapper<Domain, Entity> {

  fun asEntity(domain: Domain): Entity

  fun asDomain(entity: List<Entity>): List<Domain>
}
