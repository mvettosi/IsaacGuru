package com.isaacguru.domain.collectable.consumable.repository

import com.isaacguru.domain.collectable.consumable.model.Consumable

interface ConsumableRepository {
  suspend fun getConsumables(): List<Consumable>
}
