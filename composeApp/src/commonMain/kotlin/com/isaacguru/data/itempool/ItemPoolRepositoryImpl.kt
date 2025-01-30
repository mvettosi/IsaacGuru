package com.isaacguru.data.itempool

import com.isaacguru.data.itempool.local.ItemPoolDataSource
import com.isaacguru.data.itempool.mapper.toDomain
import com.isaacguru.data.itempool.mapper.toLocal
import com.isaacguru.data.itempool.remote.ItemPoolRemoteDataSource
import com.isaacguru.domain.itempool.model.ItemPool
import com.isaacguru.domain.itempool.repository.ItemPoolRepository

class ItemPoolRepositoryImpl(
    private val itemPoolRemoteDataSource: ItemPoolRemoteDataSource,
    private val itemPoolDataSource: ItemPoolDataSource,
) : ItemPoolRepository {

  override suspend fun getItemPools(): List<ItemPool> {
    if (itemPoolDataSource.isEmpty()) initialiseItemPools()
    return itemPoolDataSource.getAllItemPools().map { it.toDomain() }
  }

  private suspend fun initialiseItemPools() {
    itemPoolDataSource.clearItemPools()
    val itemPools = itemPoolRemoteDataSource.getDefaultItemPool()
    itemPoolDataSource.insertItemPools(itemPools.map { it.toLocal() })
  }
}
