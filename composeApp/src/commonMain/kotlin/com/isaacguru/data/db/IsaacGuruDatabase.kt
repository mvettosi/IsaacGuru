package com.isaacguru.data.db

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.isaacguru.data.db.converters.StringListTypeConverter
import com.isaacguru.data.inventory.local.InventoryLocalDataSource
import com.isaacguru.data.inventory.local.model.InventoryItemEntity
import com.isaacguru.data.inventory.local.model.InventoryItemFtsEntity
import com.isaacguru.data.itempool.local.ItemPoolDataSource
import com.isaacguru.data.itempool.local.model.ItemPoolEntity

@Database(
    entities = [InventoryItemEntity::class, InventoryItemFtsEntity::class, ItemPoolEntity::class],
    version = 3,
    autoMigrations =
        [
            AutoMigration(from = 2, to = 3),
        ])
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(IsaacGuruDatabaseConstructor::class)
abstract class IsaacGuruDatabase : RoomDatabase() {
  // Inventory
  abstract val inventoryLocalDataSource: InventoryLocalDataSource

  // Item Pools
  abstract val itemPoolDataSource: ItemPoolDataSource

  // Game Mods

  companion object {
    const val DB_NAME = "isaac_guru.db"
  }
}
