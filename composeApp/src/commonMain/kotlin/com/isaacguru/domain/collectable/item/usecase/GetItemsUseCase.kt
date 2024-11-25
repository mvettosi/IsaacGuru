package com.isaacguru.domain.collectable.item.usecase

import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.domain.collectable.item.repository.ItemRepository

class GetItemsUseCase(private val itemRepository: ItemRepository) {
  suspend operator fun invoke(itemFilters: ItemFilters) = runCatching {
    itemRepository.getItems(itemFilters = itemFilters)
  }
}
