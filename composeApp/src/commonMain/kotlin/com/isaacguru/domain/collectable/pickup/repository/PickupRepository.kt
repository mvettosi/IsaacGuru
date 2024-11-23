package com.isaacguru.domain.collectable.pickup.repository

import com.isaacguru.domain.collectable.pickup.model.Pickup

interface PickupRepository {
  suspend fun getPickups(): List<Pickup>
}
