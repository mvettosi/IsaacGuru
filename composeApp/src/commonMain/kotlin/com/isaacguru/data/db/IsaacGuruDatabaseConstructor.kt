@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.isaacguru.data.db

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object IsaacGuruDatabaseConstructor : RoomDatabaseConstructor<IsaacGuruDatabase> {
  override fun initialize(): IsaacGuruDatabase
}
