package com.isaacguru.domain.collectable.item.usecase

import com.isaacguru.domain.collectable.item.repository.ItemRepository

class GetItemPoolsUseCase(private val itemRepository: ItemRepository) {
  suspend operator fun invoke() = runCatching { itemRepository.getItemPools() }
}
