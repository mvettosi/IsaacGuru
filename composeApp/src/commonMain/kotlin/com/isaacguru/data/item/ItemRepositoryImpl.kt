package com.isaacguru.data.item

import com.isaacguru.data.db.exception.DataNotFoundException
import com.isaacguru.data.item.local.datasource.ItemDataSource
import com.isaacguru.data.item.local.mapper.toDomain
import com.isaacguru.data.item.local.mapper.toEntity
import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.domain.collectable.item.model.ItemPool
import com.isaacguru.domain.collectable.item.repository.ItemRepository

class ItemRepositoryImpl(
    private val itemDataSource: ItemDataSource,
//  private val itemPoolDataSource: ItemPoolDataSource,
) : ItemRepository {
  override suspend fun getItem(itemId: String): Item =
      itemDataSource.getItem(itemId)?.toDomain() ?: throw DataNotFoundException()

  override suspend fun getItems(itemFilters: ItemFilters): List<Item> =
      itemDataSource.getAllItems().map { it.toDomain() }

  override suspend fun getItemPools(): List<ItemPool> = TODO()
  //    itemPoolDataSource.getAllItemPools().map { it.toDomain() }

  override suspend fun setItems(items: List<Item>) {
    itemDataSource.clearItems()
    itemDataSource.insertItems(items = items.map { it.toEntity() })
  }
}
