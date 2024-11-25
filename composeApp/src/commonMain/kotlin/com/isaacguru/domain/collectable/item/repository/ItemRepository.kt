package com.isaacguru.domain.collectable.item.repository

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemFilters

interface ItemRepository {
  suspend fun getItems(itemFilters: ItemFilters): List<Item>
}
