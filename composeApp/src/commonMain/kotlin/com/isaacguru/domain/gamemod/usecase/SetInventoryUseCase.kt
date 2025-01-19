package com.isaacguru.domain.gamemod.usecase

import com.isaacguru.domain.collectable.item.repository.InventoryRepository
import com.isaacguru.domain.gamemod.model.GameAspects

class SetInventoryUseCase(private val inventoryRepository: InventoryRepository) {
  suspend operator fun invoke(gameAspects: GameAspects) = runCatching {
    inventoryRepository.setItems(gameAspects.items)
  }
}
