package com.isaacguru.data.inventory

import com.isaacguru.data.inventory.local.InventoryLocalDataSource
import com.isaacguru.data.inventory.mappers.toDomain
import com.isaacguru.data.inventory.mappers.toLocal
import com.isaacguru.data.inventory.remote.InventoryRemoteDataSource
import com.isaacguru.domain.inventory.model.InventoryItem
import com.isaacguru.domain.inventory.model.ItemFilters
import com.isaacguru.domain.inventory.repository.InventoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext

class InventoryRepositoryImpl(
    private val inventoryLocalDataSource: InventoryLocalDataSource,
    private val inventoryRemoteDataSource: InventoryRemoteDataSource
) : InventoryRepository {
  override fun getItem(itemId: String): Flow<InventoryItem> =
      inventoryLocalDataSource
          .getItem(itemId)
          .onStart {
            withContext(Dispatchers.IO) {
              if (inventoryLocalDataSource.isEmpty()) initialiseInventory()
            }
          }
          .mapNotNull { it?.toDomain() }

  override suspend fun getItems(itemFilters: ItemFilters): List<InventoryItem> {
    if (inventoryLocalDataSource.isEmpty()) initialiseInventory()
    return inventoryLocalDataSource.getAllItems().mapNotNull { it.toDomain() }
  }

  private suspend fun initialiseInventory() {
    inventoryLocalDataSource.clearItems()
    with(inventoryRemoteDataSource.getDefaultInventory()) {
      inventoryLocalDataSource.insertItems(item.mapNotNull { it.toLocal() })
      inventoryLocalDataSource.insertItems(trinket.mapNotNull { it.toLocal() })
      inventoryLocalDataSource.insertItems(pickup.mapNotNull { it.toLocal() })
      inventoryLocalDataSource.insertItems(consumable.mapNotNull { it.toLocal() })
      inventoryLocalDataSource.insertItems(machine.mapNotNull { it.toLocal() })
      inventoryLocalDataSource.insertItems(character.mapNotNull { it.toLocal() })
      inventoryLocalDataSource.insertItems(transformation.mapNotNull { it.toLocal() })
      inventoryLocalDataSource.insertItems(curse.mapNotNull { it.toLocal() })
    }
  }
}
