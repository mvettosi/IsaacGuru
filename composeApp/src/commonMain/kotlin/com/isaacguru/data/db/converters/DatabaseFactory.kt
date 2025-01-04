package com.isaacguru.data.db.converters

import androidx.room.RoomDatabase
import com.isaacguru.data.db.IsaacGuruDatabase

expect class DatabaseFactory {
  fun create(): RoomDatabase.Builder<IsaacGuruDatabase>
}
