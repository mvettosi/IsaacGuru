package com.isaacguru.data.itempool.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.isaacguru.data.itempool.local.model.ItemPoolEntity

@Dao
interface ItemPoolDataSource {
  @Query("SELECT (SELECT COUNT(*) FROM ItemPoolEntity) == 0") fun isEmpty(): Boolean
  @Query("DELETE FROM ItemPoolEntity") suspend fun clearItemPools()
  @Insert suspend fun insertItemPools(items: List<ItemPoolEntity>)
  @Query("SELECT * FROM ItemPoolEntity") suspend fun getAllItemPools(): List<ItemPoolEntity>
}
