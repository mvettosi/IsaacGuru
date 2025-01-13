package com.isaacguru.domain.collectable.item.repository

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.domain.collectable.item.model.ItemPool
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
  fun getItem(itemId: String): Flow<Item>
  suspend fun getItems(itemFilters: ItemFilters): List<Item>
  suspend fun getItemPools(): List<ItemPool>
  suspend fun setItems(items: List<Item>)
}
