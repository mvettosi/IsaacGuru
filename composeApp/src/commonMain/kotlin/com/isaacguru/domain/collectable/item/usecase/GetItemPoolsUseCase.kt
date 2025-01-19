package com.isaacguru.domain.collectable.item.usecase

import com.isaacguru.domain.itempool.repository.ItemPoolRepository

class GetItemPoolsUseCase(private val itemPoolRepository: ItemPoolRepository) {
  suspend operator fun invoke() = runCatching { itemPoolRepository.getItemPools() }
}
