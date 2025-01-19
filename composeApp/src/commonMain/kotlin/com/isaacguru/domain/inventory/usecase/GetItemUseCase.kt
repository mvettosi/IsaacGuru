package com.isaacguru.domain.inventory.usecase

import com.isaacguru.domain.inventory.model.InventoryItem
import com.isaacguru.domain.inventory.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow

class GetItemUseCase(private val inventoryRepository: InventoryRepository) {
  operator fun invoke(itemId: String): Flow<InventoryItem> = inventoryRepository.getItem(itemId)
}
