package com.isaacguru.domain.gamemod.repository

import com.isaacguru.domain.gamemod.model.GameMod

interface GameModRepository {
  suspend fun getGameMods(refresh: Boolean = false): List<GameMod>
  suspend fun getDefaultGameMod(): GameMod
}
