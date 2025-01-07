package com.isaacguru.domain.gamemod.usecase

import com.isaacguru.domain.collectable.item.repository.ItemRepository
import com.isaacguru.domain.gamemod.model.GameAspects

class SetGameAspectsUseCase(private val itemRepository: ItemRepository) {
  suspend operator fun invoke(gameAspects: GameAspects) = runCatching {
    itemRepository.setItems(gameAspects.items)
  }
}
