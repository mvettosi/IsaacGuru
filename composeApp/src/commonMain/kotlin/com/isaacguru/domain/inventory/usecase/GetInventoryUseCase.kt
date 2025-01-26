package com.isaacguru.domain.inventory.usecase

import com.isaacguru.domain.inventory.model.InventoryItem
import com.isaacguru.domain.inventory.model.Item
import com.isaacguru.domain.inventory.model.ItemFilters
import com.isaacguru.domain.inventory.model.ItemType
import com.isaacguru.domain.inventory.repository.InventoryRepository

class GetInventoryUseCase(private val inventoryRepository: InventoryRepository) {
  suspend operator fun invoke(itemFilters: ItemFilters): Result<List<InventoryItem>> = runCatching {
    inventoryRepository.getItems(query = itemFilters.query).filterItems(itemFilters)
  }

  private fun List<InventoryItem>.filterItems(itemFilters: ItemFilters): List<InventoryItem> =
      if (itemFilters.noFiltering) this
      else
          filter { item ->
            when (item) {
              is Item -> filterPedestrialItems(itemFilters, item)
              else -> false
            }
          }

  private fun filterPedestrialItems(itemFilters: ItemFilters, item: Item): Boolean =
      filterPools(itemFilters, item) && filterType(itemFilters, item)

  private fun filterPools(itemFilters: ItemFilters, item: Item): Boolean =
      itemFilters.itemPools.isEmpty() || item.itemPools.any { itemFilters.itemPools.contains(it) }

  private fun filterType(itemFilters: ItemFilters, item: Item): Boolean =
      itemFilters.itemType == null ||
          when (item) {
            is Item.Active -> itemFilters.itemType == ItemType.ACTIVE
            is Item.Passive -> itemFilters.itemType == ItemType.PASSIVE
          }
}
