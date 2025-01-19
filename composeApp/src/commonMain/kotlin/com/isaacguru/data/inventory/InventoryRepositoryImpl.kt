package com.isaacguru.data.inventory

import com.isaacguru.data.inventory.local.InventoryLocalDataSource
import com.isaacguru.data.inventory.mappers.toDomain
import com.isaacguru.data.inventory.mappers.toLocal
import com.isaacguru.data.inventory.remote.InventoryRemoteDataSource
import com.isaacguru.domain.inventory.model.InventoryItem
import com.isaacguru.domain.inventory.model.ItemFilters
import com.isaacguru.domain.inventory.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class InventoryRepositoryImpl(
    private val inventoryLocalDataSource: InventoryLocalDataSource,
    private val inventoryRemoteDataSource: InventoryRemoteDataSource
) : InventoryRepository {
  override fun getItem(itemId: String): Flow<InventoryItem> =
      inventoryLocalDataSource.getItem(itemId).mapNotNull { it?.toDomain() }

  override suspend fun getItems(itemFilters: ItemFilters): List<InventoryItem> {
    if (inventoryLocalDataSource.isEmpty()) initialiseInventory()
    return inventoryLocalDataSource.getAllItems().mapNotNull { it.toDomain() }
  }

  private suspend fun initialiseInventory() {
    inventoryLocalDataSource.clearItems()
    val defaultInventory = inventoryRemoteDataSource.getDefaultInventory()
    inventoryLocalDataSource.insertItems(defaultInventory.item.map { it.toLocal() })
    inventoryLocalDataSource.insertItems(defaultInventory.trinket.map { it.toLocal() })
    inventoryLocalDataSource.insertItems(defaultInventory.pickup.map { it.toLocal() })
    inventoryLocalDataSource.insertItems(defaultInventory.consumable.map { it.toLocal() })
    inventoryLocalDataSource.insertItems(defaultInventory.machine.map { it.toLocal() })
    inventoryLocalDataSource.insertItems(defaultInventory.character.map { it.toLocal() })
    inventoryLocalDataSource.insertItems(defaultInventory.transformation.map { it.toLocal() })
    inventoryLocalDataSource.insertItems(defaultInventory.curse.map { it.toLocal() })
  }
}
