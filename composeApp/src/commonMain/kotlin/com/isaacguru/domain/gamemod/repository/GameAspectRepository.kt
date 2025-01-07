package com.isaacguru.domain.gamemod.repository

import com.isaacguru.domain.gamemod.model.GameAspects
import com.isaacguru.domain.gamemod.model.GameMod

interface GameAspectRepository {
  suspend fun getGameAspects(mod: GameMod): GameAspects
  suspend fun getDefaultGameAspects(): GameAspects
}
