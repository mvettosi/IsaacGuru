package com.isaacguru.data.itempool

import com.isaacguru.data.itempool.remote.ItemPoolRemoteDataSource
import com.isaacguru.data.itempool.remote.mapper.toDomain
import com.isaacguru.domain.itempool.model.ItemPool
import com.isaacguru.domain.itempool.repository.ItemPoolRepository

class ItemPoolRepositoryImpl(
    private val itemPoolRemoteDataSource: ItemPoolRemoteDataSource,
//  private val itemPoolDataSource: ItemPoolDataSource,
) : ItemPoolRepository {

  override suspend fun getItemPools(): List<ItemPool> =
      itemPoolRemoteDataSource.getDefaultItemPool().map { it.toDomain() }
}
