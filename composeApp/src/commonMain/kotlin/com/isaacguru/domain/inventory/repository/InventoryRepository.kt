package com.isaacguru.domain.inventory.repository

import com.isaacguru.domain.inventory.model.InventoryItem
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
  fun getItem(itemId: String): Flow<InventoryItem>
  fun getItems(query: String? = null): Flow<List<InventoryItem>>
  suspend fun initialiseInventory()
}
