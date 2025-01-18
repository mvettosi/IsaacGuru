@file:OptIn(ExperimentalResourceApi::class)

package com.isaacguru.data.gamemod.default

import com.isaacguru.data.gamemod.remote.model.RemoteGameAspects
import com.isaacguru.data.gamemod.remote.model.RemoteItemPool
import isaacguru.composeapp.generated.resources.Res
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

class DefaultGameDataSource(private val json: Json) {
  suspend fun getDefaultGameAspects(): RemoteGameAspects =
      json.decodeFromString(getJsonString(GAME_ASPECTS_URI))

  suspend fun getDefaultItemPools(): RemoteItemPool =
      json.decodeFromString(getJsonString(GAME_POOLS_URI))

  private suspend fun getJsonString(uri: String) = Res.readBytes(uri).decodeToString()

  private companion object {
    const val GAME_MOD_URI = "files/isaac-mod.json"
    const val GAME_ASPECTS_URI = "files/isaac.json"
    const val GAME_POOLS_URI = "files/isaac-pools.json"
  }
}
