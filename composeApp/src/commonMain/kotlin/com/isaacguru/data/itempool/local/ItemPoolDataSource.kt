package com.isaacguru.data.itempool.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.isaacguru.data.itempool.local.model.ItemPoolEntity

@Dao
interface ItemPoolDataSource {
  @Insert suspend fun insertItemPools(items: List<ItemPoolEntity>)

  @Query("SELECT * FROM ItemPoolEntity") suspend fun getAllItemPools(): List<ItemPoolEntity>
}
