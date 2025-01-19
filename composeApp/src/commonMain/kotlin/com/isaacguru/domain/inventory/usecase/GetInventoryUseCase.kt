package com.isaacguru.domain.inventory.usecase

import com.isaacguru.domain.inventory.model.ItemFilters
import com.isaacguru.domain.inventory.repository.InventoryRepository

class GetInventoryUseCase(private val inventoryRepository: InventoryRepository) {
  suspend operator fun invoke(itemFilters: ItemFilters) = runCatching {
    inventoryRepository.getItems(itemFilters = itemFilters)
  }
}
