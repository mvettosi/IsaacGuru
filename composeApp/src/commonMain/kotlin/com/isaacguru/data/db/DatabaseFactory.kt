package com.isaacguru.data.db

import androidx.room.RoomDatabase

expect class DatabaseFactory {
  fun create(): RoomDatabase.Builder<IsaacGuruDatabase>
}
