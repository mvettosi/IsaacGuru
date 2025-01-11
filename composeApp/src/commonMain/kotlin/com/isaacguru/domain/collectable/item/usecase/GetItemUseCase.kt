package com.isaacguru.domain.collectable.item.usecase

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.repository.ItemRepository

class GetItemUseCase(private val itemRepository: ItemRepository) {
  suspend operator fun invoke(itemId: String): Result<Item> = runCatching {
    itemRepository.getItem(itemId)
  }
}
