package com.isaacguru.domain.inventory.usecase

import com.isaacguru.domain.itempool.model.ItemPool
import com.isaacguru.domain.itempool.repository.ItemPoolRepository

class GetItemPoolsUseCase(private val itemPoolRepository: ItemPoolRepository) {
  suspend operator fun invoke(): Result<List<ItemPool>> = runCatching {
    itemPoolRepository.getItemPools()
  }
}
