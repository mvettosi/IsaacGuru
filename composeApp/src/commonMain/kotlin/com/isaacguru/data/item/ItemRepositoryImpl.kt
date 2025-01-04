package com.isaacguru.data.item

import com.isaacguru.data.gamemod.default.DefaultGameDataSource
import com.isaacguru.data.gamemod.remote.mapper.toItem
import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemFilters
import com.isaacguru.domain.collectable.item.model.ItemPool
import com.isaacguru.domain.collectable.item.repository.ItemRepository

class ItemRepositoryImpl(
    //  private val itemDataSource: ItemDataSource,
    //  private val itemPoolDataSource: ItemPoolDataSource,
    private val defaultGameDataSource: DefaultGameDataSource
) : ItemRepository {
  override suspend fun getItems(itemFilters: ItemFilters): List<Item> =
      //    itemDataSource.getAllItems().map { it.toDomain() }
      defaultGameDataSource.getDefaultGameAspects().item.map { it.toItem() }

  override suspend fun getItemPools(): List<ItemPool> = TODO()
  //    itemPoolDataSource.getAllItemPools().map { it.toDomain() }
}
