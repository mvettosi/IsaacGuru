package com.isaacguru.data.item.local.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.isaacguru.data.item.local.model.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDataSource {
  @Query("DELETE FROM ItemEntity") suspend fun clearItems()

  @Insert suspend fun insertItems(items: List<ItemEntity>)

  @Query("SELECT * FROM ItemEntity") fun getAllItems(): List<ItemEntity>

  @Query("SELECT * FROM ItemEntity WHERE id = :id") fun getItem(id: String): Flow<ItemEntity?>
}
