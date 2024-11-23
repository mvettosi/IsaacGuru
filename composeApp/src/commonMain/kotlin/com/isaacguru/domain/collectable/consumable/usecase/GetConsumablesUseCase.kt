package com.isaacguru.domain.collectable.consumable.usecase

import com.isaacguru.domain.collectable.consumable.repository.ConsumableRepository

class GetConsumablesUseCase(private val consumableRepository: ConsumableRepository) {
  suspend operator fun invoke() = runCatching { consumableRepository.getConsumables() }
}
