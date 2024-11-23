package com.isaacguru.domain.collectable.trinket.usecase

import com.isaacguru.domain.collectable.trinket.repository.TrinketRepository

class GetTrinketsUseCase(private val trinketRepository: TrinketRepository) {
  suspend operator fun invoke() = runCatching {
     trinketRepository.getTrinkets()
  }
}