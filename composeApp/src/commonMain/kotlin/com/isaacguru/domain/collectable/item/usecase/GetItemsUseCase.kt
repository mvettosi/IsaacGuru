package com.isaacguru.domain.collectable.item.usecase

import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.domain.collectable.item.repository.InventoryRepository

class GetItemsUseCase(private val inventoryRepository: InventoryRepository) {
  suspend operator fun invoke(itemFilters: ItemFilters) = runCatching {
    inventoryRepository.getItems(itemFilters = itemFilters)
  }
}
