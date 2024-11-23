package com.isaacguru.domain.collectable.pickup.usecase

import com.isaacguru.domain.collectable.pickup.repository.PickupRepository

class GetPickupsUseCase(private val pickupRepository: PickupRepository) {
  suspend operator fun invoke() = runCatching {
     pickupRepository.getPickups()
  }
}