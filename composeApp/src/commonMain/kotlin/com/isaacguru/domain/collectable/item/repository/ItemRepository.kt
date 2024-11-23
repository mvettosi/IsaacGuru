package com.isaacguru.domain.collectable.item.repository

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemPool

interface ItemRepository {
  suspend fun getItems(itemItemPools: List<ItemPool> = emptyList()): List<Item>
}
