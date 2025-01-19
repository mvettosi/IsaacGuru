package com.isaacguru.data.inventory.remote

import com.isaacguru.data.getJsonString
import com.isaacguru.data.inventory.remote.model.InventoryRemote
import kotlinx.serialization.json.Json

class InventoryRemoteDataSource(private val json: Json) {
  suspend fun getDefaultInventory(): InventoryRemote =
      json.decodeFromString(getJsonString(DEFAULT_INVENTORY_URI))

  suspend fun getModInventory(mod: String): InventoryRemote = TODO()

  companion object {
    const val DEFAULT_MOD = "isaac"
    private const val DEFAULT_INVENTORY_URI = "files/isaac.json"
  }
}
