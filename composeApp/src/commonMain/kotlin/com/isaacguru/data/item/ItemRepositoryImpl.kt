package com.isaacguru.data.item

import com.isaacguru.data.item.local.datasource.ItemDataSource
import com.isaacguru.data.item.local.mapper.toDomain
import com.isaacguru.data.item.local.mapper.toEntity
import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.domain.collectable.item.model.ItemPool
import com.isaacguru.domain.collectable.item.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class ItemRepositoryImpl(
    private val itemDataSource: ItemDataSource,
//  private val itemPoolDataSource: ItemPoolDataSource,
) : ItemRepository {
  override fun getItem(itemId: String): Flow<Item> =
      itemDataSource.getItem(itemId).mapNotNull { it?.toDomain() }

  override suspend fun getItems(itemFilters: ItemFilters): List<Item> =
      itemDataSource.getAllItems().map { it.toDomain() }

  override suspend fun getItemPools(): List<ItemPool> = TODO()
  //    itemPoolDataSource.getAllItemPools().map { it.toDomain() }

  override suspend fun setItems(items: List<Item>) {
    itemDataSource.clearItems()
    itemDataSource.insertItems(items = items.map { it.toEntity() })
  }
}
