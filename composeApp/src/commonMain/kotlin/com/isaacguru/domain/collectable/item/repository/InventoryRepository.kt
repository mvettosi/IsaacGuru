package com.isaacguru.domain.collectable.item.repository

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.domain.gamemod.model.GameAspects
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
  fun getInventory(): Flow<GameAspects>
  fun getItem(itemId: String): Flow<Item>
  suspend fun getItems(itemFilters: ItemFilters): List<Item>
  suspend fun setItems(items: List<Item>)
}
