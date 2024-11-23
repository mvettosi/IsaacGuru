package com.isaacguru.domain.collectable.item.usecase

import com.isaacguru.domain.collectable.item.model.Pool
import com.isaacguru.domain.collectable.item.repository.ItemRepository

class GetItemsUseCase(private val itemRepository: ItemRepository) {
  suspend operator fun invoke(itemPools: List<Pool> = emptyList()) = runCatching {
     itemRepository.getItems(itemPools = itemPools)
  }
}