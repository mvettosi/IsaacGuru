package com.isaacguru.domain.collectable.item.usecase

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow

class GetItemUseCase(private val inventoryRepository: InventoryRepository) {
  operator fun invoke(itemId: String): Flow<Item> = inventoryRepository.getItem(itemId)
}
