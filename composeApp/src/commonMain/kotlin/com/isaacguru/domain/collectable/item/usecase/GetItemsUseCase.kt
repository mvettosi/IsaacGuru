package com.isaacguru.domain.collectable.item.usecase

import com.isaacguru.domain.collectable.item.model.ItemPool
import com.isaacguru.domain.collectable.item.repository.ItemRepository

class GetItemsUseCase(private val itemRepository: ItemRepository) {
  suspend operator fun invoke(itemItemPools: List<ItemPool> = emptyList()) = runCatching {
    itemRepository.getItems(itemItemPools = itemItemPools)
  }
}
