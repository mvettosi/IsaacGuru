package com.isaacguru.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.isaacguru.data.db.converters.StringListTypeConverter
import com.isaacguru.data.item.local.datasource.ItemDataSource
import com.isaacguru.data.item.local.model.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(IsaacGuruDatabaseConstructor::class)
abstract class IsaacGuruDatabase : RoomDatabase() {
  // Game Aspects
  abstract val itemDataSource: ItemDataSource

  // Item Pools
  //  abstract val itemPoolDataSource: ItemPoolDataSource

  // Game Mods

  companion object {
    const val DB_NAME = "isaac_guru.db"
  }
}
