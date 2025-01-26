package com.isaacguru.domain.inventory.repository

import com.isaacguru.domain.inventory.model.InventoryItem
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
  fun getItem(itemId: String): Flow<InventoryItem>
  suspend fun getItems(query: String? = null): List<InventoryItem>
}
