package com.isaacguru.domain.collectable.trinket.repository

import com.isaacguru.domain.collectable.trinket.model.Trinket

interface TrinketRepository {
  suspend fun getTrinkets(): List<Trinket>
}