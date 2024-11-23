package com.isaacguru.domain.collectable.item.repository

import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.Pool

interface ItemRepository {
  suspend fun getItems(itemPools: List<Pool> = emptyList()): List<Item>
}