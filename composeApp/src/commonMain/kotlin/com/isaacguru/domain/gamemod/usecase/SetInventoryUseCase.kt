package com.isaacguru.domain.gamemod.usecase

import com.isaacguru.domain.gamemod.model.GameAspects
import com.isaacguru.domain.inventory.repository.InventoryRepository

class SetInventoryUseCase(private val inventoryRepository: InventoryRepository) {
  suspend operator fun invoke(gameAspects: GameAspects) = runCatching {
    //    inventoryRepository.setItems(gameAspects.items)
  }
}
