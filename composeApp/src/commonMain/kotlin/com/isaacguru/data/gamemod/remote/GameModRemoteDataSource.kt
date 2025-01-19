package com.isaacguru.data.gamemod.remote

import com.isaacguru.data.gamemod.remote.model.RemoteGameMod
import com.isaacguru.data.getJsonString
import kotlinx.serialization.json.Json

class GameModRemoteDataSource(private val json: Json) {
  suspend fun getDefaultGameMod(): RemoteGameMod =
      json.decodeFromString(getJsonString(GAME_MOD_URI))

  private companion object {
    const val GAME_MOD_URI = "files/isaac-mod.json"
  }
}
