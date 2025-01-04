package com.isaacguru.data.db.converters

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.isaacguru.data.db.IsaacGuruDatabase

actual class DatabaseFactory(private val context: Context) {
  actual fun create(): RoomDatabase.Builder<IsaacGuruDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(IsaacGuruDatabase.DB_NAME)

    return Room.databaseBuilder(context = appContext, name = dbFile.absolutePath)
  }
}
