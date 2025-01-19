package com.isaacguru.data.itempool.remote

import com.isaacguru.data.getJsonString
import com.isaacguru.data.itempool.remote.model.ItemPoolRemote
import kotlinx.serialization.json.Json

class ItemPoolRemoteDataSource(private val json: Json) {
  suspend fun getDefaultItemPool(): Map<String, ItemPoolRemote> =
      json.decodeFromString(getJsonString(DEFAULT_ITEMPOOL_URI))

  private companion object {
    const val DEFAULT_ITEMPOOL_URI = "files/isaac-pools.json"
  }
}
