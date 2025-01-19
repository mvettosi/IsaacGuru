package com.isaacguru.data.inventory.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.isaacguru.data.inventory.local.model.InventoryItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryLocalDataSource {
  @Query("SELECT (SELECT COUNT(*) FROM InventoryItemEntity) == 0") fun isEmpty(): Boolean

  @Query("DELETE FROM InventoryItemEntity") suspend fun clearItems()

  @Insert suspend fun insertItems(items: List<InventoryItemEntity>)

  @Query("SELECT * FROM InventoryItemEntity") fun getAllItems(): List<InventoryItemEntity>

  @Query("SELECT * FROM InventoryItemEntity WHERE id_raw = :id")
  fun getItem(id: String): Flow<InventoryItemEntity?>
}
