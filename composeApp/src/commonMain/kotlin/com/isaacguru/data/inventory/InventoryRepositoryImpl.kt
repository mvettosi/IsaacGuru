package com.isaacguru.data.inventory

import co.touchlab.kermit.Logger
import com.isaacguru.data.inventory.local.InventoryLocalDataSource
import com.isaacguru.data.inventory.mappers.toDomain
import com.isaacguru.data.inventory.mappers.toLocal
import com.isaacguru.data.inventory.remote.InventoryRemoteDataSource
import com.isaacguru.domain.inventory.model.InventoryItem
import com.isaacguru.domain.inventory.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class InventoryRepositoryImpl(
    private val inventoryLocalDataSource: InventoryLocalDataSource,
    private val inventoryRemoteDataSource: InventoryRemoteDataSource
) : InventoryRepository {
  override fun getItem(itemId: String): Flow<InventoryItem> =
      inventoryLocalDataSource.getItem(itemId).mapNotNull { it?.toDomain() }

  override fun getItems(query: String?): Flow<List<InventoryItem>> {
    return inventoryLocalDataSource.getAllItems().map { items ->
      items.mapNotNull { it.toDomain() }
    }
  }

  override suspend fun initialiseInventory() {
    if (inventoryLocalDataSource.isEmpty()) {
      Logger.i { "Initialising Inventory" }
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
    } else {
      Logger.i { "Inventory already initialised, skipping" }
    }
  }
}
