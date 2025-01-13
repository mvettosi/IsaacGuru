package com.isaacguru.domain.collectable.item.usecase

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetItemUseCase(private val itemRepository: ItemRepository) {
  operator fun invoke(itemId: String): Flow<Item> = itemRepository.getItem(itemId)
}
