package com.isaacguru.domain.inventory.repository

import com.isaacguru.domain.inventory.model.InventoryItem
import com.isaacguru.domain.inventory.model.ItemFilters
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
  fun getItem(itemId: String): Flow<InventoryItem>
  suspend fun getItems(itemFilters: ItemFilters): List<InventoryItem>
}
