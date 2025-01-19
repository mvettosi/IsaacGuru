package com.isaacguru.data.inventory

import com.isaacguru.data.inventory.local.InventoryLocalDataSource
import com.isaacguru.data.inventory.local.mapper.toDomain
import com.isaacguru.data.inventory.local.mapper.toEntity
import com.isaacguru.data.inventory.remote.InventoryRemoteDataSource
import com.isaacguru.data.inventory.remote.mapper.toDomain
import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.domain.collectable.item.repository.InventoryRepository
import com.isaacguru.domain.gamemod.model.GameAspects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull

class InventoryRepositoryImpl(
    private val inventoryLocalDataSource: InventoryLocalDataSource,
    private val inventoryRemoteDataSource: InventoryRemoteDataSource
) : InventoryRepository {
  override fun getInventory(): Flow<GameAspects> = flow {
    emit(inventoryRemoteDataSource.getDefaultInventory().toDomain())
  }

  override fun getItem(itemId: String): Flow<Item> =
      inventoryLocalDataSource.getItem(itemId).mapNotNull { it?.toDomain() }

  override suspend fun getItems(itemFilters: ItemFilters): List<Item> =
      inventoryLocalDataSource.getAllItems().map { it.toDomain() }

  override suspend fun setItems(items: List<Item>) {
    inventoryLocalDataSource.clearItems()
    inventoryLocalDataSource.insertItems(items = items.map { it.toEntity() })
  }
}
