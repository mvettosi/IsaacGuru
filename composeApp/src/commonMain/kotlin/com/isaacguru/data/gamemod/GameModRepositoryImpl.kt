package com.isaacguru.data.gamemod

import com.isaacguru.domain.gamemod.model.GameMod
import com.isaacguru.domain.gamemod.repository.GameModRepository

class GameModRepositoryImpl() : GameModRepository {
  override suspend fun getGameMods(refresh: Boolean): List<GameMod> {
    TODO("Not yet implemented")
  }

  override suspend fun getDefaultGameMod(): GameMod {
    TODO("Not yet implemented")
  }
}
