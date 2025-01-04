package com.isaacguru.domain.collectable.item.repository

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.domain.collectable.item.model.ItemPool

interface ItemRepository {
  suspend fun getItems(itemFilters: ItemFilters): List<Item>
  suspend fun getItemPools(): List<ItemPool>
}
